package com.lemzeeyyy.booklistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lemzeeyyy.booklistapp.model.Users;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameET, passwordET, emailET;
    private Button registerBtn;
    private FirebaseAuth auth;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeWidgets();
        auth = FirebaseAuth.getInstance();
        registerBtn.setOnClickListener(v -> {
            String email = emailET.getText().toString().trim();
            String password = passwordET.getText().toString().trim();
            String username = usernameET.getText().toString().trim();
            if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password) || TextUtils.isEmpty(username)){
                Toast.makeText(RegisterActivity.this, "Please Fill all Fields",
                        Toast.LENGTH_SHORT).show();
            }else {
                registerUser(username,email,password);
            }
        });

    }

    private void registerUser(final String username, String email, String password) {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        FirebaseUser user = auth.getCurrentUser();
                       // assert user != null;
                        String userId = user.getUid();
                        reference = FirebaseDatabase.getInstance()
                                .getReference("MyUsers")
                                .child(userId);

                        HashMap<String, Object> usersInfo = new HashMap<>();
                        usersInfo.put("id", userId);
                        usersInfo.put("imageURL", "default");
                        usersInfo.put("username",username);

                        reference.setValue(usersInfo)
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()){
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).addOnFailureListener(e -> Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
                    }else {
                        Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initializeWidgets() {
        usernameET = findViewById(R.id.usernameETRegister);
        passwordET = findViewById(R.id.passwordETRegister);
        emailET = findViewById(R.id.emailETRegister);
        registerBtn = findViewById(R.id.buttonRegister);
    }
}