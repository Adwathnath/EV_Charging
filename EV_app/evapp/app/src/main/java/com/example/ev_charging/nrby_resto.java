package com.example.ev_charging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

public class nrby_resto extends AppCompatActivity {

    ListView ls1;
    SharedPreferences sh;
    String url;
    ArrayList<String> name, place, phone, email, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nrby_resto);

        ls1=findViewById(R.id.rest);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        url = "http://" + sh.getString("ip", "") + ":5000/view_nrby_resto";
        RequestQueue queue = Volley.newRequestQueue(nrby_resto.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {

                    JSONArray ar = new JSONArray(response);
                    name = new ArrayList<>();
                    place = new ArrayList<>();
                    phone = new ArrayList<>();
                    email = new ArrayList<>();
                    image = new ArrayList<>();


                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        name.add(jo.getString("name"));
                        image.add(jo.getString("image"));
                        place.add(jo.getString("place"));
                        phone.add(jo.getString("phone"));
                        email.add(jo.getString("email"));



                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    ls1.setAdapter(new custom_nrby_station(nrby_resto.this, name, image, place, phone, email));
//                    l1.setOnItemClickListener(view_fecility.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(nrby_resto.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lati",LocationService.lati);
                params.put("longi",LocationService.logi);

                return params;
            }
        };
        queue.add(stringRequest);
    }
}