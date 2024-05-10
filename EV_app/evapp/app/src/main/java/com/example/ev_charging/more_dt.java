package com.example.ev_charging;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;
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

public class more_dt extends AppCompatActivity {
    TextView b1,b3,b4,tt;
    String sid,bid,lati,longi;
    SharedPreferences sp;
    String url,stts;
    TableRow t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_dt);

        b1=findViewById(R.id.textView46);
        b3=findViewById(R.id.textView47);
        b4=findViewById(R.id.textView45);
        tt=findViewById(R.id.textView50);
//        t1=findViewById(R.id.t1);
        stts=getIntent().getStringExtra("status");
        sid=getIntent().getStringExtra("sid");
        bid=getIntent().getStringExtra("bid");
        lati=getIntent().getStringExtra("latitude");
        longi=getIntent().getStringExtra("longitude");
        if(stts.equalsIgnoreCase("cancelled")|(stts.equalsIgnoreCase("completed"))){
            b4.setVisibility(View.INVISIBLE);
            tt.setVisibility(View.INVISIBLE);

        }

        sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?q="+lati+","+longi));
                startActivity(intent);
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik=new Intent(getApplicationContext(),send_feedback.class);
                ik.putExtra("sid", sid);
                startActivity(ik);
            }
        });


//        b3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent ik=new Intent(getApplicationContext(),payment.class);
//                startActivity(ik);
//            }
//        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                AlertDialog.Builder ald=new AlertDialog.Builder(more_dt.this);
                ald.setTitle("Are you sure?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                try
                                {
                                    RequestQueue queue = Volley.newRequestQueue(more_dt.this);
                                    url = "http://" + sp .getString("ip","") + ":5000/cancel_booking";

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
                                                    Toast.makeText(more_dt.this, "booking cancelled", Toast.LENGTH_SHORT).show();
                                                    Intent i=new Intent(getApplicationContext(),home2.class);
                                                    startActivity(i);

                                                } else {

                                                    Toast.makeText(more_dt.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }


                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(getApplicationContext(),bid, Toast.LENGTH_LONG).show();


                                            Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                                        }
                                    }) {
                                        @Override
                                        protected Map<String, String> getParams() {
                                            Map<String, String> params = new HashMap<String, String>();
                                            params.put("bid", bid);


                                            return params;
                                        }
                                    };
                                    queue.add(stringRequest);





                                }
                                catch(Exception e)
                                {
                                    Toast.makeText(getApplicationContext(),e+"",Toast.LENGTH_LONG).show();
                                }

                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                Intent i=new Intent(getApplicationContext(),more_dt.class);

                                startActivity(i);
                            }
                        });

                AlertDialog al=ald.create();
                al.show();
            }
        });

    }
}