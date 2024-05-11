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
import android.widget.RatingBar;
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

public class send_feedback extends AppCompatActivity {
EditText e1;
RatingBar r;
Button b1;
String url,rate,sid;
SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);

        e1=findViewById(R.id.editTextTextPersonName);
        r=findViewById(R.id.ratingBar);
        b1=findViewById(R.id.button3);
        sid=getIntent().getStringExtra("sid");
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String feedback=e1.getText().toString();
                rate=String.valueOf(r.getRating());
                if (feedback.equalsIgnoreCase("")) {
                    e1.setError("Enter Your Feedback");
                }
                else
                {

                RequestQueue queue = Volley.newRequestQueue(send_feedback.this);
                url = "http://" + sh.getString("ip","") + ":5000/android/sent_feedback";

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
                                Toast.makeText(send_feedback.this,"feedback added",Toast.LENGTH_LONG).show();

                                Intent ik = new Intent(getApplicationContext(),home2.class);
                                startActivity(ik);

                            } else {

                                Toast.makeText(send_feedback.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                        params.put("feedback", feedback);
                        params.put("rating", rate);
                        params.put("s_id", sid);
                        params.put("lid", sh.getString("lid",""));






                        return params;
                    }
                };
                queue.add(stringRequest);

            }}
        });

    }
}