package com.example.ev_charging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
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

public class MainActivity extends AppCompatActivity {
 EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
 Spinner s1;
 RadioButton r1,r2;
 Button b1;
 String gender;
 String url;
 SharedPreferences sh;
 String arr[]={"Thiruvananthapuram","Kollam","Pathanamthitta","Kottayam","Idukki","Ernakulam","Alappuzha","Thrissur","Palakkad","Malappuram","Kozhikode","Wayanad","Kannur","Kasaragod"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=findViewById(R.id.editTextTextPersonName);
        e2=findViewById(R.id.editTextTextPersonName3);
        e3=findViewById(R.id.editTextTextPersonName8);
        e4=findViewById(R.id.editTextTextPersonName9);
        e5=findViewById(R.id.editTextTextPersonName14);
        e6=findViewById(R.id.editTextNumber2);
        e7=findViewById(R.id.editTextTextEmailAddress);
        e8=findViewById(R.id.editTextPhone);
        e9=findViewById(R.id.editTextTextPassword3);
        s1=findViewById(R.id.spinner);
        r1=findViewById(R.id.radioButton2);
        r2=findViewById(R.id.radioButton);
        b1=findViewById(R.id.button);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ArrayAdapter<String> ad=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,arr);
        s1.setAdapter(ad);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fname=e1.getText().toString();
                String lname=e2.getText().toString();
                String hname=e3.getText().toString();
                String place=e4.getText().toString();
                String post=e5.getText().toString();
                String pin=e6.getText().toString();
                String email=e7.getText().toString();
                String phone=e8.getText().toString();
                String psw=e9.getText().toString();
                String dis=s1.getSelectedItem().toString();
                if(r1.isChecked()) {
                    gender = r1.getText().toString();
                }
                else{
                    gender = r2.getText().toString();
                }


                if (fname.equalsIgnoreCase("")) {
                    e1.setError("Enter Your Name");
                } else if (hname.equalsIgnoreCase("")) {
                    e3.setError("Enter Your house name");
                }
                else if (place.equalsIgnoreCase("")) {
                    e4.setError("Enter Your house name");
                }
                else if (post.equalsIgnoreCase("")) {
                    e5.setError("Enter Your house place");
                }
                else if(pin.length()!=6)
                {
                    e6.setError("Invalid pin");
                    e6.requestFocus();
                }

                else if(email.equalsIgnoreCase(""))
                {
                    e7.setError("enter email");
                    e7.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    e7.setError("Enter Valid Email");
                    e7.requestFocus();
                }
                else if(phone.equalsIgnoreCase(""))
                {
                    e8.setError("Enter contact");
                    e8.requestFocus();
                }
                else if(phone.length()!=10)
                {
                    e8.setError("Invalid phoneno");
                    e8.requestFocus();
                }
                else if(psw.equalsIgnoreCase(""))
                {
                    e9.setError("enter your password");
                    e9.requestFocus();
                }











                else
                {


                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/android/user_registration";

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
                                    Toast.makeText(MainActivity.this, "successfully registered", Toast.LENGTH_SHORT).show();


                                    Intent ik = new Intent(getApplicationContext(), login.class);
                                    startActivity(ik);

                                } else {

                                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                            params.put("fname", fname);
                            params.put("lname", lname);
                            params.put("hname", hname);
                            params.put("place", place);
                            params.put("post", post);
                            params.put("pin", pin);
                            params.put("email", email);
                            params.put("radio", gender);
                            params.put("pswd", psw);
                            params.put("phone", phone);
                            params.put("dis", dis);

                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }

            }
        });



    }
}