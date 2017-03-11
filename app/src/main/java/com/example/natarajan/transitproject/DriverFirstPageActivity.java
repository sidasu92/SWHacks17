package com.example.natarajan.transitproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverFirstPageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonFullBus;
    private Button buttonNotFull;

    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    private RadioGroup radioGroup3;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_first_page);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        buttonFullBus = (Button) findViewById(R.id.buttonFullBus);
        buttonNotFull = (Button) findViewById(R.id.buttonNotFull);

        radioGroup1 = (RadioGroup) findViewById(R.id.radiogroup1);
        radioGroup2 = (RadioGroup) findViewById(R.id.radiogroup2);
        radioGroup3 = (RadioGroup) findViewById(R.id.radiogroup3);

        if(radioGroup1.isEnabled() && (radioGroup2.isEnabled() || radioGroup3.isEnabled())) {
            buttonFullBus.setEnabled(true);
            buttonNotFull.setEnabled(true);
            buttonFullBus.setOnClickListener(this);
            buttonNotFull.setOnClickListener(this);
        }
        else
        {
            buttonFullBus.setEnabled(false);
            buttonNotFull.setEnabled(false);
            Toast.makeText(this,"Please select the Bus and Direction of the bus",Toast.LENGTH_SHORT).show();
        }
    }

    private void setBusFullMode()
    {
        OrbitBus orbitBus1;
        String message = "Bus Full";
        if(this.radioGroup1.getCheckedRadioButtonId() == R.id.radioMercury)
        {
            String orbitBus = "Mercury";
            this.radioGroup3.clearCheck();
            if(this.radioGroup2.getCheckedRadioButtonId() == R.id.radioEast) {
                String direction = "East";
                orbitBus1 = new OrbitBus(orbitBus,direction,message);
                databaseReference.setValue(orbitBus1);
            }
            else
            {
                String direction = "West";
                orbitBus1 = new OrbitBus(orbitBus,direction,message);
                databaseReference.setValue(orbitBus1);
            }
        }
        else
        {
            String orbitBus = "Jupiter";
            this.radioGroup2.clearCheck();
            if(this.radioGroup3.getCheckedRadioButtonId() == R.id.radioNorth) {
                String direction = "North";
                orbitBus1 = new OrbitBus(orbitBus,direction,message);
                databaseReference.setValue(orbitBus1);
            }
            else
            {
                String direction = "South";
                orbitBus1 = new OrbitBus(orbitBus,direction,message);
                databaseReference.setValue(orbitBus1);
            }
        }
        Toast.makeText(this,"bus full success",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(this.radioGroup1.getCheckedRadioButtonId() == R.id.radioMercury)
            this.radioGroup3.clearCheck();
        if(this.radioGroup1.getCheckedRadioButtonId() == R.id.radioJupiter)
            this.radioGroup2.clearCheck();
    }

    private void setBusNotFullMode()
    {
        String message = "Bus Not Full";
        OrbitBus orbitBus1;
        if(this.radioGroup1.getCheckedRadioButtonId() == R.id.radioMercury)
        {
            String orbitBus = "Mercury";
            this.radioGroup3.clearCheck();
            if(this.radioGroup2.getCheckedRadioButtonId() == R.id.radioEast) {
                String direction = "East";
                orbitBus1 = new OrbitBus(orbitBus,direction,message);
                databaseReference.setValue(orbitBus1);
            }
            else
            {
                String direction = "West";
                orbitBus1 = new OrbitBus(orbitBus,direction,message);
                databaseReference.setValue(orbitBus1);
            }
        }
        else
        {
            String orbitBus = "Jupiter";
            this.radioGroup2.clearCheck();
            if(this.radioGroup3.getCheckedRadioButtonId() == R.id.radioNorth) {
                String direction = "North";
                orbitBus1 = new OrbitBus(orbitBus,direction,message);
                databaseReference.setValue(orbitBus1);
            }
            else
            {
                String direction = "South";
                orbitBus1 = new OrbitBus(orbitBus,direction,message);
                databaseReference.setValue(orbitBus1);
            }
        }

        Toast.makeText(this,"bus not full success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        if (v == buttonFullBus)
        {
            setBusFullMode();
        }
        if (v == buttonNotFull)
        {
            setBusNotFullMode();
        }

    }
}
