package com.example.ev_charging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class payment extends AppCompatActivity {
EditText e1,e2,e3,e4,e5;
Button b1;
SharedPreferences sh;
String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        e1=findViewById(R.id.editTextNumber3);
        e2=findViewById(R.id.editTextTextPersonName5);
        e3=findViewById(R.id.editTextTextPersonName6);
        e4=findViewById(R.id.editTextTextPersonName7);
        e5=findViewById(R.id.editTextNumber4);

        e5.setText("145");
        e5.setEnabled(false);
        b1=findViewById(R.id.button6);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String acno=e1.getText().toString();
                String acname=e2.getText().toString();
                String ifsc=e3.getText().toString();
                String key=e4.getText().toString();
                String amount=e5.getText().toString();








                RequestQueue queue = Volley.newRequestQueue(payment.this);
                url = "http://" + sh.getString("ip","") + ":5000/android/payment";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("task");

                            if (res.equalsIgnoreCase("success")) {

                                Intent ik = new Intent(getApplicationContext(),home.class);
                                startActivity(ik);

                            } else {

                                Toast.makeText(payment.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                        params.put("ac_no", acname);
                        params.put("ac_name", acno);
                        params.put("ifsc", ifsc);
                        params.put("key", key);
                        params.put("amount", amount);


                        params.put("date", sh.getString("date","0"));
                        params.put("time", sh.getString("time","0"));
                        params.put("kid", sh.getString("kid","0"));
                        params.put("u_id", sh.getString("lid","0"));



                        return params;
                    }
                };
                queue.add(stringRequest);

            }
        });



    }
}