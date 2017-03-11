package com.example.natarajan.transitproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChooseRole extends AppCompatActivity implements View.OnClickListener{

    public Button driver;
    public Button user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);

        driver = (Button) findViewById(R.id.driver);
        user = (Button) findViewById(R.id.user);

        driver.setOnClickListener(this);
        user.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user:
                finish();
                startActivity(new Intent(this,SignInUser.class));
                break;

            case R.id.driver:
                startActivity(new Intent(this,SignInDriver.class));
                break;
        }
    }
    /*
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }*/
}
