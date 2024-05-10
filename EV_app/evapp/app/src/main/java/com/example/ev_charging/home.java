package com.example.ev_charging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        b1=findViewById(R.id.button7);
        b2=findViewById(R.id.button9);
        b3=findViewById(R.id.button10);
        b4=findViewById(R.id.button11);
        b5=findViewById(R.id.button12);
        b6=findViewById(R.id.button13);
        b7=findViewById(R.id.button14);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik=new Intent(getApplicationContext(),nrby_statin.class);
                startActivity(ik);

            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik=new Intent(getApplicationContext(),nrby_resto.class);
                startActivity(ik);

            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik=new Intent(getApplicationContext(),view_fecility.class);
                startActivity(ik);

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik=new Intent(getApplicationContext(),view_booking_status.class);
                startActivity(ik);




            }
        });


        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik=new Intent(getApplicationContext(),view_reply.class);
                startActivity(ik);

            }
        });


        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik=new Intent(getApplicationContext(),send_feedback.class);
                startActivity(ik);

            }
        });



        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik=new Intent(getApplicationContext(),payment.class);
                startActivity(ik);


            }
        });

    }
}