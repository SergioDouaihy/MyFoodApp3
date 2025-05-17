package com.example.myfoodapp.activities;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapp.R;
import com.example.myfoodapp.adapters.TableAdapter;
import com.example.myfoodapp.models.TableModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TableAdapter adapter;
    private List<TableModel> tableList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list);

        recyclerView = findViewById(R.id.tablesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tableList = new ArrayList<>();
        adapter = new TableAdapter(tableList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        loadTablesFromFirestore();
    }

    private void loadTablesFromFirestore() {
        db.collection("Tables")
                .whereEqualTo("canOrder", true)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    tableList.clear();

                    Log.d("TableActivity", "Total tables found: " + queryDocumentSnapshots.size());
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                        String tableNumber = snapshot.getString("tableNumber");
                        Object isOccupiedObj = snapshot.get("isOccupied");

                        boolean isOccupied = false; // default fallback

                        if (isOccupiedObj instanceof Boolean) {
                            isOccupied = (Boolean) isOccupiedObj;
                        } else if (isOccupiedObj instanceof String) {
                            isOccupied = Boolean.parseBoolean((String) isOccupiedObj);
                        } else if (isOccupiedObj instanceof Number) {
                            isOccupied = ((Number) isOccupiedObj).intValue() != 0;
                        }

                        Log.d("TableActivity", "Loaded table: " + tableNumber + ", isOccupied: " + isOccupied);

                        tableList.add(new TableModel(tableNumber, isOccupied));
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> Log.e("FirestoreError", "Error loading tables", e));
    }
}