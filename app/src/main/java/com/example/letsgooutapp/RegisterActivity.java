package com.example.letsgooutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText emailText;
    private EditText dateText;
    private EditText passwordText;
    private EditText password2Text;

    private String username;
    private String email;
    private String date;
    private String password;
    private String password2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameText = findViewById(R.id.RegisterUsername);
        emailText = findViewById(R.id.RegisterEmail);
        dateText = findViewById(R.id.RegisterDate);
        passwordText = findViewById(R.id.RegisterPassword);
        password2Text = findViewById(R.id.RegisterPassword2);

        username = usernameText.getText().toString();
        email = emailText.getText().toString();
        date = dateText.getText().toString();
        password = dateText.getText().toString();
        password2 = dateText.getText().toString();

    }


    public void submitRegistration(View view) {
        finish();

        Context context = getApplicationContext();
        Account account = new Account(username, email,date,password);

        String text = "Nothing happened, back to login";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();


    }
}