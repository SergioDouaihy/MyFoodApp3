package com.example.myfoodapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.concurrent.atomic.AtomicReference;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapp.R;
import com.example.myfoodapp.adapters.MenuAdapter;
import com.example.myfoodapp.models.MenuItemModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {
    private FirebaseFirestore firestore;
    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private List<MenuItemModel> menuItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView = findViewById(R.id.recyclerViewMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        menuItemList = new ArrayList<>();
        menuAdapter = new MenuAdapter(this, menuItemList);
        recyclerView.setAdapter(menuAdapter);

        firestore = FirebaseFirestore.getInstance();
        fetchMenuItemsFromFirestore();

        Button btnAddItem = findViewById(R.id.btnAddMenuItem);
        btnAddItem.setOnClickListener(v -> showAddItemDialog());
    }

    private void fetchMenuItemsFromFirestore() {
        firestore.collection("Menu")
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    List<MenuItemModel> menuList = new ArrayList<>();
                    for (DocumentSnapshot doc : querySnapshot) {
                        String id = doc.getId(); // Firestore doc ID
                        String name = doc.getString("name");
                        String imageUrl = doc.getString("imageUrl");
                        String price = doc.getString("price");
                        String position = doc.getString("position"); // Your app's field

                        menuList.add(new MenuItemModel(id, name, imageUrl, price, position));
                    }

                    menuAdapter = new MenuAdapter(MenuActivity.this, menuList);
                    recyclerView.setAdapter(menuAdapter);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(MenuActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }


    private void showAddItemDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_menu_item, null);

        EditText editTextName = dialogView.findViewById(R.id.editTextItemName);
        EditText editTextPrice = dialogView.findViewById(R.id.editTextItemPrice);
        EditText editTextPosition = dialogView.findViewById(R.id.editTextItemPosition);
        EditText editTextImageUrl = dialogView.findViewById(R.id.editTextItemImageUrl);
        Button btnAdd = dialogView.findViewById(R.id.btnAddItem);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();

        btnAdd.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String price = editTextPrice.getText().toString().trim();
            String position = editTextPosition.getText().toString().trim();
            String imageUrl = editTextImageUrl.getText().toString().trim();

            if (name.isEmpty() || imageUrl.isEmpty() || price.isEmpty() || position.isEmpty()) {
                Toast.makeText(MenuActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("name", name);
            itemMap.put("imageUrl", imageUrl);
            itemMap.put("price", price);
            itemMap.put("position", position);

            firestore.collection("Menu")
                    .add(itemMap)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(MenuActivity.this, "Item added to Firestore", Toast.LENGTH_SHORT).show();
                        fetchMenuItemsFromFirestore(); // ✅ REFRESH UI with new data
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(MenuActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });

            dialog.dismiss();
        });
    }

}
