package com.example.huser.government;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient mFusedLocationClient;
    TextView tv2,tv3;
    int i=1;
    FloatingActionButton fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestpermission();
        tv2=findViewById(R.id.textView2);
        tv3=findViewById(R.id.textView3);
        fb=findViewById(R.id.floatingActionButton2);
        DatabaseReference dr= FirebaseDatabase.getInstance().getReference();
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tv2.setText("\nLevel of water : \t" +
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

                    tv3.setText("Flood not occurs");
                    tv3.setTextColor(Color.GREEN);
                    fb.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#008000")));
                }
                else{
                    tv3.setText("Flood occur");
                    tv3.setTextColor(Color.RED);
                    fb.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Thread t=new Thread(){
            @Override
            public void run() {
                //super.run();
                while(!isInterrupted()){
                    try {
                        Thread.sleep(20000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Toast.makeText(MainActivity.this, "value is"+i, Toast.LENGTH_SHORT).show();
                                //i++;
                                //tv.setText(String.valueOf(i));
                                hai();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        t.start();
       /* DatabaseReference dr=FirebaseDatabase.getInstance().getReference();
        dr.child("hai").setValue("bye");
        Toast.makeText(this, "sucess", Toast.LENGTH_SHORT).show();*/
    }
   public void hai()
   {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            Toast.makeText(this, "sorry", Toast.LENGTH_SHORT).show();
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            //locate=location;
                            //tv.setText(location.toString());
                            DatabaseReference dr= FirebaseDatabase.getInstance().getReference();
                            dr.child("gov1").child("loc").setValue(""+location.getLatitude()+","+location.getLongitude());
                            //dr.child("hai").setValue("bye");
                            //Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(MainActivity.this, "location is "+location, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    private void requestpermission()
    {
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
    public void search_defect_location(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=&daddr="+"14.4493, 78.2339"));
        startActivity(intent);
    }
    public void safe(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=&daddr="+"16.989065,82.247467"));
        startActivity(intent);
    }
}
