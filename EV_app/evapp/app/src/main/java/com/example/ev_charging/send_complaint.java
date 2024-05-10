package com.example.ev_charging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class send_complaint extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText e1;
    Button b1;
    SharedPreferences sh;
    String url,sid;
    Spinner s1;
    ArrayList<String> cname,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_complaint);

        e1 = findViewById(R.id.editTextTextPersonName13);
        b1 = findViewById(R.id.button15);
        s1 = findViewById(R.id.spinner4);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        RequestQueue queue = Volley.newRequestQueue(send_complaint.this);
        url = "http://" + sh.getString("ip", "") + ":5000/view_station";
        s1.setOnItemSelectedListener(send_complaint.this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {

                    JSONArray ar = new JSONArray(response);

                    cname = new ArrayList<>(ar.length());
                    id = new ArrayList<>(ar.length());
                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        cname.add(jo.getString("name"));
                        id.add(jo.getString("s_id"));


                    }

                    ArrayAdapter<String> ad = new ArrayAdapter<>(send_complaint.this, android.R.layout.simple_spinner_item, cname);
                    s1.setAdapter(ad);

                    // l1.setAdapter(new custom2(Monitoring_signal.this,notification,date));

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String complaint=e1.getText().toString();
                if (complaint.equalsIgnoreCase("")) {
                    e1.setError("Enter Your Complaint");
                }
                else
                {



                RequestQueue queue = Volley.newRequestQueue(send_complaint.this);
                url = "http://" + sh.getString("ip","") + ":5000/add_complaint";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("task");

                            if (res.equalsIgnoreCase("valid")) {
                                Toast.makeText(send_complaint.this, "sent successfully", Toast.LENGTH_SHORT).show();

                                Intent ik = new Intent(getApplicationContext(),view_reply.class);
                                startActivity(ik);

                            } else {

                                Toast.makeText(send_complaint.this, "not sent", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("complaint", complaint);
                        params.put("lid", sh.getString("lid",""));
                        params.put("sid", sid);






                        return params;
                    }
                };
                queue.add(stringRequest);
            }}
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sid=id.get(i);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }




}