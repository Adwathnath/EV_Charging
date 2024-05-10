package com.example.ev_charging;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class book extends AppCompatActivity {

    EditText e1,e2;
    Button b1;
    SharedPreferences sh;
    String url;
    Spinner s1;
    String arr[]={"5.00 AM","6.00 AM","7.00 AM","8.00 AM","9.00 AM","10.00 AM","11.00 AM","12.00 PM","1.00 PM","2.00 PM","3.00 PM","4.00 PM","5.00 PM","6.00 PM","7.00 PM","8.00 PM","9.00 PM","10.00 PM","11.00 PM","12.00 AM","1.00 AM","2.00 AM","3.00 AM","4.00 AM"};
    String kid;
    String dts="",ddts="";
    DatePickerDialog datepicker;
    TimePickerDialog mTimePicker;
    ArrayList<String> slot,sid,cs,floorr;
    final Calendar myCalendar= Calendar.getInstance();
    int day, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;
    String status="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        s1=findViewById(R.id.spinner3);
        e2=findViewById(R.id.editTextTextPersonName10);
        b1=findViewById(R.id.button5);
        kid=(getIntent().getStringExtra("id"));
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ArrayAdapter<String> ad=new ArrayAdapter<>(book.this, android.R.layout.simple_list_item_1,arr);
        s1.setAdapter(ad);

        e2.setOnClickListener(new View.OnClickListener() {
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
                DatePickerDialog datepicker = new DatePickerDialog(book.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dts = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " ";
                                e2.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis());
                datepicker.show();

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date=e2.getText().toString();
                String time=s1.getSelectedItem().toString();

                String amt = "145";
                RequestQueue queue = Volley.newRequestQueue(book.this);
                url = "http://" + sh.getString("ip","") + ":5000/book_slot";

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
                                Intent in = new Intent(getApplicationContext(),PaymentActivity.class);
                                SharedPreferences.Editor ed=sh.edit();
                                ed.putString("amt",amt);
                                ed.putString("sid",kid);
                                ed.putString("date",date);
                                ed.putString("time",time);
                                ed.commit();
                                startActivity(in);



                            } else {

                                Toast.makeText(book.this, "slot not available", Toast.LENGTH_SHORT).show();

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
                        params.put("s_id",kid );
                        params.put("bdate", date);
                        params.put("btime", time);

                        return params;
                    }
                };
                queue.add(stringRequest);

////                Intent in = new Intent(getApplicationContext(),payment.class);
////                startActivity(in);
//
//
//                RequestQueue queue = Volley.newRequestQueue(book.this);
//                url = "http://" + sh.getString("ip","") + ":5000/book_slot";
//
//                // Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the response string.
//                        Log.d("+++++++++++++++++", response);
//                        try {
//                            JSONObject json = new JSONObject(response);
//                            String res = json.getString("task");
//
//
//                            if (res.equalsIgnoreCase("success")) {
//
//                                    SharedPreferences.Editor edp = sh.edit();
//                                    edp.putString("date", date);
//                                    edp.putString("time", time);
//                                    edp.putString("kid", kid);
//                                    edp.commit();
//                                    Intent ikk = new Intent(getApplicationContext(),PaymentActivity.class);
//                                    startActivity(ikk);
//
//
//
//                            } else {
//
//                                Toast.makeText(book.this, "Slot not available", Toast.LENGTH_SHORT).show();
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//
//                        Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
//                    }
//                }) {
//                    @Override
//                    protected Map<String, String> getParams() {
//                        Map<String, String> params = new HashMap<String, String>();
//
//                        params.put("bdate", date);
//                        params.put("btime", time);
//                        params.put("u_id", sh.getString("lid","0"));
//                        params.put("s_id", kid);
//
//
//
//
//
//                        return params;
//                    }
//                };
//                queue.add(stringRequest);

            }
        });


    }
}