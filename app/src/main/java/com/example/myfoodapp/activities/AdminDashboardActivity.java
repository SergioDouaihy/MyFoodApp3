package com.example.myfoodapp.activities;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;

import android.os.Bundle;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfoodapp.MainActivity;

import com.example.myfoodapp.R;  // Corrected import

public class AdminDashboardActivity extends AppCompatActivity {

    // Declare Buttons

    Button btnMenu, btnTables, btnOrders, btnAnalytics;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_dashboard); // Reference to your layout

        // Initialize Buttons

        btnMenu = findViewById(R.id.btnMenu);

        btnTables = findViewById(R.id.btnTables);

        btnOrders = findViewById(R.id.btnOrders);

//        btnAnalytics = findViewById(R.id.btnAnalytics);

        // Set Button Click Listeners (These can navigate to other Activities)

        btnMenu.setOnClickListener(v -> startActivity(new Intent(this, MenuActivity.class)));

        btnTables.setOnClickListener(v -> startActivity(new Intent(this, TableActivity.class)));

        btnOrders.setOnClickListener(v -> startActivity(new Intent(this, OrdersActivity.class)));

//        btnAnalytics.setOnClickListener(v -> startActivity(new Intent(this, AnalyticsActivity.class)));

    }

}

