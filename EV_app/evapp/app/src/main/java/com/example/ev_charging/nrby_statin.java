package com.example.ev_charging;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class nrby_statin extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView l1;
    SharedPreferences sh;
    String url;
    ArrayList<String> name, place, phone, email, image,s_id;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nrby_statin);

        l1=findViewById(R.id.stin);
        String lati=LocationService.lati;
        String longi=LocationService.logi;
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        url = "http://" + sh.getString("ip", "") + ":5000/view_nrby_station";
        RequestQueue queue = Volley.newRequestQueue(nrby_statin.this);

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
                    s_id = new ArrayList<>();


                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        name.add(jo.getString("name"));
                        place.add(jo.getString("place"));
                        phone.add(jo.getString("phone"));
                        email.add(jo.getString("email"));
                        image.add(jo.getString("image"));
                        s_id.add(jo.getString("s_id"));



                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom_nrby_station(nrby_statin.this, name,image,place, phone, email));
                    l1.setOnItemClickListener(nrby_statin.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(nrby_statin.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lati",lati);
                params.put("longi",longi);

                return params;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        pos=i;


        AlertDialog.Builder ald=new AlertDialog.Builder(nrby_statin.this);
        ald.setTitle("Booking Details")
                .setPositiveButton("current bookings ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        try
                        {
                            Intent ik=new Intent(getApplicationContext(),crt_bookings.class);
                            ik.putExtra("id",s_id.get(i));

                            startActivity(ik);




                        }
                        catch(Exception e)
                        {
                            Toast.makeText(getApplicationContext(),e+"",Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .setNegativeButton("book", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent ik=new Intent(getApplicationContext(),book.class);
                        ik.putExtra("id",s_id.get(i));

                        startActivity(ik);


                    }
                });

        AlertDialog al=ald.create();
        al.show();






    }
}