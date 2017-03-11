package com.example.natarajan.transitproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInDriver extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignIn;
    //private TextView textViewSignUp;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_user);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(), DriverFirstPageActivity.class));
        }

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);

        buttonSignIn.setOnClickListener(this);

    }

    public void driverLogin()
    {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            //if email is empty, prompt the user
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            //and stop the function from any further execution
            return;
        }

        if(TextUtils.isEmpty(password)) {
            //if password is empty, prompt the user
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            //and stop the function from any further execution
            return;
        }

        //user entered email and password
        progressDialog.setMessage("please wait ..");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //login is successful, take user to the main screen
                    finish();
                    startActivity(new Intent(getApplicationContext(), DriverFirstPageActivity.class));
                }
                else
                {
                    Toast.makeText(SignInDriver.this,"wrong credentials, please try again", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == buttonSignIn)
        {
            driverLogin();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, ChooseRole.class));
    }
}
