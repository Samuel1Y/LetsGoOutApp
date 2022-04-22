package com.example.letsgooutapp.Model;

public class Account {
    private String username;
    private String password;
    private String dateOfBirth;
    private String email;

    public Account(String username, String email, String dateOfBirth, String password)
    {
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }
}
