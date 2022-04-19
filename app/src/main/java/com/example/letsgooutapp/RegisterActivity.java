package com.example.letsgooutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    public void submitRegistration(View view) {
        finish();

        Context context = getApplicationContext();
        String text = "Nothing happened, back to login";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();


    }
}