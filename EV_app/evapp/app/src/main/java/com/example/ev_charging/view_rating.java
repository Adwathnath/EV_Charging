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

public class view_rating extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView l1;
    SharedPreferences sp;
    String url;
    ArrayList<String> name, place, phone, email, image,rating,s_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rating);


        l1=findViewById(R.id.lv22);
        sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        url = "http://" + sp.getString("ip", "") + ":5000/android/view_rating";
        RequestQueue queue = Volley.newRequestQueue(view_rating.this);

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
                    rating= new ArrayList<>();
                    s_id= new ArrayList<>();




                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        name.add(jo.getString("name"));
                        image.add(jo.getString("image"));
                        place.add(jo.getString("place"));
                        phone.add(jo.getString("phone"));
                        email.add(jo.getString("email"));
                        rating.add(jo.getString("r"));
                        s_id.add(jo.getString("s_id"));




                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom_view_rating(view_rating.this, name, image, place, phone, email,rating));
                    l1.setOnItemClickListener(view_rating.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_rating.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
//                params.put("lati",LocationService.lati)`;
//                params.put("longi",LocationService.logi);

                return params;
            }
        };
        queue.add(stringRequest);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent ik=new Intent(getApplicationContext(),book.class);
        ik.putExtra("id",s_id.get(i));
        startActivity(ik);

    }
}