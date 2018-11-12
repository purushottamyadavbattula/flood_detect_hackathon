package com.example.huser.citizen;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Contact extends AppCompatActivity {
    String Number,Number2;
    TextView tv,tv1;
    EditText ed,ed1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        tv=findViewById(R.id.textView7);
        tv1=findViewById(R.id.textView8);
        ed=findViewById(R.id.editText);
        ed1=findViewById(R.id.editText2);
        DatabaseReference dr= FirebaseDatabase.getInstance().getReference();
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tv.setText("\nname : "+dataSnapshot.child("gov1").child("name").getValue()+"\nphno : "+dataSnapshot.child("gov1").child("phno").getValue());
            Number=""+dataSnapshot.child("gov1").child("phno").getValue();
            Number2=""+dataSnapshot.child("gov2").child("phno").getValue();
                tv1.setText("\nname : "+dataSnapshot.child("gov2").child("name").getValue()+"\nphno : "+dataSnapshot.child("gov2").child("phno").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void call_person(View view) {
        Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+Number));
        startActivity(i);
    }

    public void send_sms(View view) {
        Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address",""+Number);
        smsIntent.putExtra("sms_body",""+ed.getText());
        startActivity(smsIntent);
    }

    public void call_gov2(View view) {
        Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+Number2));
        startActivity(i);

    }

    public void sms_gov2(View view) {
        Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address",""+Number2);
        smsIntent.putExtra("sms_body",""+ed.getText());
        startActivity(smsIntent);
    }
}
