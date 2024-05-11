package com.example.ev_charging;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class crt_bookings extends AppCompatActivity {

    ListView l1;
    EditText e;
    Button b1;
    String url,ssid;
    ArrayList<String> slot,sid,bid,lati,longi,date;
    SharedPreferences sh;
    String dts="",ddts="",da;
    DatePickerDialog datepicker;
    TimePickerDialog mTimePicker;

    final Calendar myCalendar= Calendar.getInstance();
    int day, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crt_bookings);

        l1=findViewById(R.id.lv25);
        e=findViewById(R.id.e1);
        b1=findViewById(R.id.button16);
        ssid=(getIntent().getStringExtra("id"));

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                status="1";
//                showTruitonTimePickerDialog(v);
//                showTruitonDatePickerDialog(v);
//                new DatePickerDialog(booking_slot.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog datepicker = new DatePickerDialog(crt_bookings.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dts = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " ";
                                e.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis());
                datepicker.show();

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                da=e.getText().toString();
                url ="http://"+sh.getString("ip", "") + ":5000/android/currentbooking";
                RequestQueue queue = Volley.newRequestQueue(crt_bookings.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++",response);
                        try {

                            JSONArray ar=new JSONArray(response);
                            slot= new ArrayList<>();



                            for(int i=0;i<ar.length();i++)
                            {
                                JSONObject jo=ar.getJSONObject(i);
                                slot.add(jo.getString("time_slot"));



                            }

                             ArrayAdapter<String> ad=new ArrayAdapter<>(crt_bookings.this,android.R.layout.simple_list_item_1,slot);
                            l1.setAdapter(ad);

//                            l1.setAdapter(new custom_booking_sts(crt_bookings.this,charging_center,status,time,date));
//                    l1.setOnItemClickListener(crt_bookings.this);

                        } catch (Exception e) {
                            Log.d("=========", e.toString());
                        }


                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(crt_bookings.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("sid",ssid);
                        params.put("date",da);

                        params.put("u_id", sh.getString("lid",""));
                        return params;

                    }
                };
                queue.add(stringRequest);

            }
        });






    }
}