package com.example.ev_charging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class view_fecility extends AppCompatActivity implements AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener {
 ListView l1;
 Spinner s1;

 String url,sid;
 ArrayList<String> cname,id;
 SharedPreferences sh;
    ArrayList<String> charging_center,fecility,description,image,s_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fecility);

        s1=findViewById(R.id.spinner2);

        l1=findViewById(R.id.l3);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        RequestQueue queue = Volley.newRequestQueue(view_fecility.this);

        url ="http://"+sh.getString("ip","")+":5000/view_station";
        s1.setOnItemSelectedListener(view_fecility.this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);

                    cname= new ArrayList<>(ar.length());
                    id= new ArrayList<>(ar.length());
                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        cname.add(jo.getString("name"));
                        id.add(jo.getString("s_id"));


                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<>(view_fecility.this,android.R.layout.simple_spinner_item,cname);
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

        sid=id.get(i);
        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        url ="http://"+sh.getString("ip", "") + ":5000/view_fecilites";
        RequestQueue queue1 = Volley.newRequestQueue(view_fecility.this);

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    charging_center= new ArrayList<>();
                    fecility= new ArrayList<>();
                    description= new ArrayList<>();
                    image=new ArrayList<>();
                    s_id=new ArrayList<>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        charging_center.add(jo.getString("name"));
                        fecility.add(jo.getString("fecility"));
                        description.add(jo.getString("description"));
                        image.add(jo.getString("image"));
                        s_id.add(jo.getString("s_id"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new customfacility(view_fecility.this,charging_center,image,fecility,description));
                    l1.setOnItemClickListener(view_fecility.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_fecility.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("cid",sid);

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
        Intent ik =new Intent(getApplicationContext(),view_image.class);
        ik.putExtra("sid",s_id);
        ik.putExtra("image",image.get(i));
        startActivity(ik);
    }
}