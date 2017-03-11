package com.example.natarajan.transitproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BusStatusActivity extends AppCompatActivity {

    private TextView textViewBusStatus;
    private TextView textViewBusDirection;
    private TextView textViewBusName;

    private Firebase firebaseRef;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_status);

        Firebase.setAndroidContext(this);

        textViewBusStatus = (TextView)findViewById(R.id.textViewBusStatus);
        textViewBusDirection = (TextView)findViewById(R.id.textViewBusDirection);
        textViewBusName = (TextView)findViewById(R.id.textViewBusName);

        firebaseRef = new Firebase("https://mobilecomputing-18b44.firebaseio.com");
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, SignInUser.class));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Firebase msgRef = firebaseRef.child("message");
        Firebase dirRef = firebaseRef.child("busDirection");
        Firebase orbRef = firebaseRef.child("orbitBus");

        msgRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message = dataSnapshot.getValue(String.class);
                textViewBusStatus.setText(message);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        dirRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String direction = dataSnapshot.getValue(String.class);
                textViewBusDirection.setText(direction);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        orbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String busName = dataSnapshot.getValue(String.class);
                textViewBusName.setText(busName);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, MapsActivity.class));
    }
}
