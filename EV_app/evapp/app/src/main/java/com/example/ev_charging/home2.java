package com.example.ev_charging;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ev_charging.R;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class home2 extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{



    ImageButton B1,B2,B3,B4,B5;
    ImageView I1;
    TextView T1,t2;
    SharedPreferences sh;
    String url,name,photo;
    ListView L1;


    ArrayList<String>heading,content;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.textView);
        TextView navemail = (TextView) headerView.findViewById(R.id.textView1);
        navUsername.setText(sh.getString("name",""));
        navemail.setText(sh.getString("email",""));
//        View linearLayout=navigationView.inflateHeaderView(R.layout.nav_header_home);
        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


//        T1.setText("xcxcvv");

//T1.setText("ffffffff");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);



        navigationView.setNavigationItemSelectedListener(this);



    }














    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home2, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.nav_home)
        {
            Intent i=new Intent(getApplicationContext(),nrby_statin.class);
            startActivity(i);

        }
        if(id==R.id.nav_gallery)
        {
            Intent i=new Intent(getApplicationContext(),nrby_resto.class);
            startActivity(i);

        }
        if(id==R.id.nav_slideshow)
        {
            Intent i=new Intent(getApplicationContext(),view_fecility.class);
            startActivity(i);

        }
        if(id==R.id.bookslot)
        {
            Intent i=new Intent(getApplicationContext(),view_booking_status.class);
            startActivity(i);

        }
        if(id==R.id.camplaints)
        {
            Intent i=new Intent(getApplicationContext(),view_reply.class);
            startActivity(i);

        }
        if(id==R.id.logout)
        {
            Intent i=new Intent(getApplicationContext(),login.class);
            startActivity(i);

        }
        if(id==R.id.stss)
        {
            Intent i=new Intent(getApplicationContext(),place_st.class);
            startActivity(i);

        }

        if(id==R.id.rate)
        {
            Intent i=new Intent(getApplicationContext(),view_rating.class);
            startActivity(i);

        }






        return false;
    }
}