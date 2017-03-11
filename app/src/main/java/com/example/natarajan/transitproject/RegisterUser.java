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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignUp;
    private TextView textViewSignIn;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        //creating an instance of the progress dialog
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);

        buttonSignUp.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);
    }

    public void registerUser()
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
        progressDialog.setMessage("Registering the user ..");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //user successfully registered
                    Toast.makeText(RegisterUser.this,"Registered Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                }
                else
                {
                    Toast.makeText(RegisterUser.this,"Try again !", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if(v == buttonSignUp)
        {
            //register the user
            registerUser();
        }
        if(v == textViewSignIn)
        {
            //already registered, take user to the sign-in page
            finish();;
            startActivity(new Intent(this, SignInUser.class));
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, SignInUser.class));
    }
}
