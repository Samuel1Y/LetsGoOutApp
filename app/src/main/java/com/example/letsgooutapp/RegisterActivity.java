package com.example.letsgooutapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.letsgooutapp.Model.Account;
import com.example.letsgooutapp.ViewModel.RegisterViewModel;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private RegisterViewModel registerViewModel;

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

        loadInfo();

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        registerViewModel.getRegisteredUser().observe(this, new Observer<Account>() {
            @Override
            public void onChanged(Account account) {
                if (account != null) {
                    if (account.getUsername() != null) {
                        Context context = getApplicationContext();
                        String text = "Account added " + registerViewModel.getRegisteredUser().getValue();
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context, text, duration).show();
                    }
                }
            }
        });
    }


    public void submitRegistration(View view) {
        // finish();

        loadInfo();
        if(password.equals(password2)){
            Account account = new Account(username, email,date,password);
            registerViewModel.addNewAccount(account);
        }
        else{
            Context context = getApplicationContext();
            String text = "Passwords do not match";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        }

    }

    private void loadInfo(){
        username = usernameText.getText().toString();
        email = emailText.getText().toString();
        date = dateText.getText().toString();
        password = dateText.getText().toString();
        password2 = dateText.getText().toString();
    }

}