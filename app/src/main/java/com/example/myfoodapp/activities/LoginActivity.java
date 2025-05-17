package com.example.myfoodapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfoodapp.MainActivity;
import com.example.myfoodapp.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class LoginActivity extends AppCompatActivity {

    EditText emailField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.editText2);
        passwordField = findViewById(R.id.editText3);
    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

    public void mainActivity(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("Users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        boolean matchFound = false;

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String dbEmail = document.getString("email");
                            String dbPassword = document.getString("Password");
                            String role = document.getString("Role");

                            if (dbEmail != null && dbPassword != null &&
                                    dbEmail.equalsIgnoreCase(email) &&
                                    dbPassword.equalsIgnoreCase(password)) {

                                matchFound = true;

                                if (role != null) {
                                    role = role.trim(); // Just in case of extra spaces
                                    Log.d("ROLE_CHECK", "Role from Firestore: '" + role + "'");

                                    if (role.equalsIgnoreCase("Admin")) {
                                        Toast.makeText(LoginActivity.this, "Admin Login", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "User Login", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Role not assigned", Toast.LENGTH_SHORT).show();
                                }

                                break;
                            }
                        }

                        if (!matchFound) {
                            Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            Log.d("FIREBASE", "No matching email and password found");
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Failed to retrieve users", Toast.LENGTH_SHORT).show();
                        Log.e("FIREBASE", "Error getting documents: ", task.getException());
                    }
                });
    }
}
