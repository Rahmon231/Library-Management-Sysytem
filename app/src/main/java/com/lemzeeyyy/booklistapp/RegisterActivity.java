package com.lemzeeyyy.booklistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class RegisterActivity extends AppCompatActivity {
    private EditText username, password, email;
    private Button register;
    private FirebaseAuth auth;
    private DatabaseReference users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeWidgets();
    }

    private void initializeWidgets() {
        username = findViewById(R.id.usernameETRegister);
        password = findViewById(R.id.passwordETRegister);
        email = findViewById(R.id.emailETRegister);
        register = findViewById(R.id.buttonRegister);
    }
}