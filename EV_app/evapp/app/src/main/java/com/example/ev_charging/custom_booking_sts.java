package com.example.ev_charging;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
public class custom_booking_sts extends BaseAdapter{
    private Context context;
    ArrayList<String> t1,t2,t3,t4;
    SharedPreferences sh;
    public custom_booking_sts(Context applicationContext, ArrayList<String> t1, ArrayList<String> t2, ArrayList<String> t3, ArrayList<String> t4) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.t1=t1;
        this.t2=t2;
        this.t3=t3;
        this.t4=t4;

        sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return t1.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getItemViewType(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_custom_booking_sts, null);

        }
        else
        {
            gridView=(View)convertview;

        }
        TextView tx1=(TextView)gridView.findViewById(R.id.t1);
        TextView tx2=(TextView)gridView.findViewById(R.id.t2);
        TextView tx3=(TextView)gridView.findViewById(R.id.t3);
        TextView tx4=(TextView)gridView.findViewById(R.id.t4);



        tx1.setText(t1.get(position));
        tx2.setText(t2.get(position));
        tx3.setText(t3.get(position));
        tx4.setText(t4.get(position));



        tx1.setTextColor(Color.BLACK);

        if (t2.get(position).equalsIgnoreCase("paid")){
            tx2.setTextColor(Color.BLUE);
        }
        else if (t2.get(position).equalsIgnoreCase("cancelled")){
            tx2.setTextColor(Color.GRAY);
        }
        else if (t2.get(position).equalsIgnoreCase("completed")){
            tx2.setTextColor(Color.GREEN);
        }

        else{
            tx2.setTextColor(Color.RED);
        }

        tx3.setTextColor(Color.BLACK);
        tx4.setTextColor(Color.BLACK);



        return gridView;
    }
}