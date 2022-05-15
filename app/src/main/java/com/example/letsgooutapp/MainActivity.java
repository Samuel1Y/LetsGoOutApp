package com.example.letsgooutapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.letsgooutapp.Dao.RegisterDao;
import com.example.letsgooutapp.Model.Account;
import com.example.letsgooutapp.ViewModel.RegisterViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private String username;
    private String password;

    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameText = findViewById(R.id.LoginUsername);
        passwordText = findViewById(R.id.LoginPassword);
        username = usernameText.getText().toString();
        password = passwordText.getText().toString();
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        registerViewModel.getRegisteredUser().observe(this, new Observer<Account>() {
            @Override
            public void onChanged(Account account) {
                if (account != null) {
                    if (account.getUsername() != null) {
                        Context context = getApplicationContext();
                        String text = "Account logged in " + registerViewModel.getRegisteredUser().getValue();
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context, text, duration).show();
                        goToHomeActivity();
                    }
                }
            }
        });

    }

    public void logIn(View view) {
        Button loginButton = view.findViewById(R.id.LogInButton);
        username = usernameText.getText().toString();
        password = passwordText.getText().toString();
        registerViewModel.getUser(username).observe(this, new Observer<Account>() {
            @Override
            public void onChanged(Account account) {
                if (account != null) {
                    if (account.getUsername() != null) {
                        if(account.getPassword().equals(password)) {
                            registerViewModel.setRegisteredUser(account);
                            goToHomeActivity();
                        }
                        else{
                            Context context = getApplicationContext();
                            String text = "Password is wrong";
                            int duration = Toast.LENGTH_SHORT;
                            Toast.makeText(context, text, duration).show();
                        }
                    }
                }
            }
        });
    }

    public void moveToRegisterView(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }


    public void openMap(View view) {
        startActivity(new Intent(this, MapActivity.class));
    }

    public void openHome(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    private void goToHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}