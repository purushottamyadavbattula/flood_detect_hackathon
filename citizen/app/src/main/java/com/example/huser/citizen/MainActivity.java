package com.example.huser.citizen;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView tv1,tv2;
    static String loc;
    FloatingActionButton fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseReference  dr= FirebaseDatabase.getInstance().getReference();
        tv1=findViewById(R.id.textView2);
        tv2=findViewById(R.id.textView3);
        fb=findViewById(R.id.floatingActionButton2);
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loc= (String) dataSnapshot.child("gov1").child("loc").getValue();
                tv1.setText("\nLevel of water : \t" +
                dataSnapshot.child("sensorvalues").child("height").getValue()+
                        "\nHumidity : \t"+
                dataSnapshot.child("sensorvalues").child("humidity").getValue()+
                        "\nTemperature : \t"+
                dataSnapshot.child("sensorvalues").child("temperature").getValue()+
                        "\nWater-Flow : \t"+
                dataSnapshot.child("sensorvalues").child("waterflow").getValue()+
                        "\nFlood occurs : \t"+
                dataSnapshot.child("sensorvalues").child("poffloods").getValue()+
                        "\nFlood not occur : \t"+
                dataSnapshot.child("sensorvalues").child("pofnoflood").getValue()+
                        "\nPrediction : \t"+
                dataSnapshot.child("sensorvalues").child("prediction").getValue()
                );
                /*Toast.makeText(MainActivity.this, ""+dataSnapshot.child("prediction").getValue().equals("1"), Toast.LENGTH_SHORT).show();*/
                if(Integer.parseInt((String) dataSnapshot.child("sensorvalues").child("prediction").getValue())==0) {

                    tv2.setText("Flood not occurs");
                    tv2.setTextColor(Color.GREEN);
                    fb.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#008000")));
                }
                else{
                    tv2.setText("Flood occur");
                    tv2.setTextColor(Color.RED);
                    fb.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void search_employee(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=&daddr="+loc));
        startActivity(intent);
    }

    public void search_defect_location(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=&daddr="+"14.4493, 78.2339"));
        startActivity(intent);
    }

    public void gotonext(View view) {
        startActivity(new Intent(this,Contact.class));
    }

    public void safe(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=&daddr="+"16.989065,82.247467"));
        startActivity(intent);
    }
}
