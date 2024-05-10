package com.example.ev_charging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ip extends AppCompatActivity {
    EditText e1;
    Button b1;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);

        e1=findViewById(R.id.editTextTextPersonName12);
        b1=findViewById(R.id.button8);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ip=e1.getText().toString();
                SharedPreferences.Editor edp = sh.edit();
                edp.putString("ip", ip);
                edp.commit();
                Intent ik=new Intent(getApplicationContext(),login.class);
                startActivity(ik);

            }
        });
    }
}