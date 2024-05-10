package com.example.ev_charging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class view_booking_status extends AppCompatActivity implements AdapterView.OnItemClickListener {
ListView l1;
String url;
ArrayList<String> charging_center,status,time,sid,bid,lati,longi,date;
SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking_status);
        l1=findViewById(R.id.l2);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        url ="http://"+sh.getString("ip", "") + ":5000/view_book_sts";
        RequestQueue queue = Volley.newRequestQueue(view_booking_status.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    charging_center= new ArrayList<>();
                    status= new ArrayList<>();
                    time= new ArrayList<>();
                    sid= new ArrayList<>();
                    date= new ArrayList<>();
                    bid= new ArrayList<>();
                    lati= new ArrayList<>();
                    longi= new ArrayList<>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        charging_center.add(jo.getString("name"));
                        status.add(jo.getString("bstat"));
                        time.add(jo.getString("time_slot"));
                        sid.add(jo.getString("s_id"));
                        date.add(jo.getString("date"));
                        bid.add(jo.getString("b_id"));
                        lati.add(jo.getString("latitude"));
                        longi.add(jo.getString("longitude"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom_booking_sts(view_booking_status.this,charging_center,status,time,date));
                    l1.setOnItemClickListener(view_booking_status.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_booking_status.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("u_id", sh.getString("lid",""));
                return params;

            }
        };
        queue.add(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent ik=new Intent(getApplicationContext(),more_dt.class);
        ik.putExtra("sid", sid.get(i));
        ik.putExtra("bid", bid.get(i));
        ik.putExtra("latitude", lati.get(i));
        ik.putExtra("longitude", longi.get(i));
        ik.putExtra("status", status.get(i));
        startActivity(ik);
    }
}