package com.example.myfoodapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfoodapp.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    EditText fullNameField, emailField, passwordField;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fullNameField = findViewById(R.id.editText);
        emailField = findViewById(R.id.editText2);
        passwordField = findViewById(R.id.editText3);
        db = FirebaseFirestore.getInstance();
    }

    public void login_reg(View view) {
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }

    public void mainActivity(View view) {
        String fullName = fullNameField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        // Basic empty checks
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate full name
        if (fullName.contains(" ") || !fullName.equals(fullName.toLowerCase())) {
            Toast.makeText(this, "Full name must be lowercase with no spaces", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate password
        if (!isValidPassword(password)) {
            Toast.makeText(this, "Password must be at least 8 characters and include uppercase, lowercase, number, and special character", Toast.LENGTH_LONG).show();
            return;
        }

        // Check if email or full name already exists
        db.collection("Users")
                .whereEqualTo("email", email)
                .get()
                .addOnSuccessListener(emailSnapshot -> {
                    if (!emailSnapshot.isEmpty()) {
                        Toast.makeText(this, "Email already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        db.collection("Users")
                                .whereEqualTo("Full_Name", fullName)
                                .get()
                                .addOnSuccessListener(nameSnapshot -> {
                                    if (!nameSnapshot.isEmpty()) {
                                        Toast.makeText(this, "Full name already used", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("Full_Name", fullName);
                                        user.put("email", email);
                                        user.put("Password", password);
                                        user.put("Role", "User");

                                        db.collection("Users")
                                                .add(user)
                                                .addOnSuccessListener(documentReference -> {
                                                    Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                                                    finish();
                                                })
                                                .addOnFailureListener(e -> {
                                                    Toast.makeText(this, "Registration failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                });
                                    }
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(this, "Failed to check full name", Toast.LENGTH_SHORT).show();
                                });
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to check email", Toast.LENGTH_SHORT).show();
                });
    }
    private boolean isValidPassword(String password) {
        // At least 8 characters, one uppercase, one lowercase, one digit, one special char
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return password.matches(passwordPattern);
    }


}
