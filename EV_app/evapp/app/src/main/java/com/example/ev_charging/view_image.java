package com.example.ev_charging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;

public class view_image extends AppCompatActivity {
    ImageView im;
    String img;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        im=findViewById(R.id.imageView3);
        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        img=getIntent().getStringExtra("image");
        java.net.URL thumb_u;
        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000/static/ccfile/"+img);
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            im.setImageDrawable(thumb_d);
        }

         catch (Exception e)
            {
                Log.d("errsssssssssssss",""+e);
            }
        }

    }
