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
import android.widget.ListView;
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

public class place_st extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    Spinner s1;
    ListView l1;
    String url,url1,sid,place;
    ArrayList<String> cname,id;
    SharedPreferences sh;
    ArrayList<String> name,phone,email,image,s_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_st);

        s1=findViewById(R.id.spinner5);
        l1=findViewById(R.id.ls22);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        RequestQueue queue = Volley.newRequestQueue(place_st.this);

        url ="http://"+sh.getString("ip","")+":5000/viewplace";
        s1.setOnItemSelectedListener(place_st.this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);

                    cname= new ArrayList<>(ar.length());

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        cname.add(jo.getString("place"));




                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<>(place_st.this,android.R.layout.simple_spinner_item,cname);
                    s1.setAdapter(ad);

                    // l1.setAdapter(new custom2(Monitoring_signal.this,notification,date));

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);








    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        place=cname.get(i);
        url1 = "http://" + sh.getString("ip", "") + ":5000/view_cc";
        RequestQueue queue1 = Volley.newRequestQueue(place_st.this);

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {

                    JSONArray ar = new JSONArray(response);
                    name = new ArrayList<>();
                    phone = new ArrayList<>();
                    email = new ArrayList<>();
                    image = new ArrayList<>();
                    s_id = new ArrayList<>();


                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        name.add(jo.getString("name"));
                        phone.add(jo.getString("phone"));
                        email.add(jo.getString("email"));
                        image.add(jo.getString("image"));
                        s_id.add(jo.getString("s_id"));



                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom_place_st(place_st.this, name,image, phone,email));
                    l1.setOnItemClickListener(place_st.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(place_st.this, "err" + error, Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("place",cname.get(i));
//                params.put("longi",longi);

                return params;
            }
        };
        queue1.add(stringRequest1);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent ik=new Intent(getApplicationContext(),book.class);
        ik.putExtra("id",s_id.get(i));

        startActivity(ik);
    }
}
