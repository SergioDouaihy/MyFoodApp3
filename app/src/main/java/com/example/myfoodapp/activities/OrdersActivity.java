package com.example.myfoodapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfoodapp.R;
import com.example.myfoodapp.models.OrderModel;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.Map;

public class OrdersActivity extends AppCompatActivity {

    private ListView ordersListView;
    private ArrayList<OrderModel> orderList;
    private OrdersAdapter adapter;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders); // Ensure it has a ListView with id ordersListView

        ordersListView = findViewById(R.id.ordersListView);
        orderList = new ArrayList<>();
        adapter = new OrdersAdapter();
        ordersListView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        // Directly fetching Firestore data here instead of a separate method
        db.collection("orders").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                orderList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {

                    String tableNumber = document.getString("tableNumber");
                    String status = document.getString("status");
                    Double totalPrice = document.getDouble("totalPrice");

                    // Parse cartItems list
                    ArrayList<String> itemList = new ArrayList<>();
                    ArrayList<Object> cartItems = (ArrayList<Object>) document.get("cartItems");

                    if (cartItems != null) {
                        for (Object itemObj : cartItems) {
                            if (itemObj instanceof Map) {
                                Map<String, Object> item = (Map<String, Object>) itemObj;
                                String name = (String) item.get("name");
                                Long quantity = (Long) item.get("quantity");
                                String price = (String) item.get("price");

                                itemList.add(quantity + "x " + name + " (" + price + ")");
                            }
                        }
                    }

                    String itemsText = String.join(", ", itemList);

                    if (tableNumber != null && status != null && totalPrice != null) {
                        orderList.add(new OrderModel(tableNumber, itemsText, status, totalPrice));
                    }
                }

                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(OrdersActivity.this, "Failed to load orders.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Custom Adapter Class
    class OrdersAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return orderList.size();
        }

        @Override
        public Object getItem(int position) {
            return orderList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            TextView orderDetailsText;
            Button cancelOrderButton;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(OrdersActivity.this)
                        .inflate(R.layout.order_item, parent, false);
                holder = new ViewHolder();
                holder.orderDetailsText = convertView.findViewById(R.id.orderDetailsText);
                holder.cancelOrderButton = convertView.findViewById(R.id.cancelOrderButton);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            OrderModel order = orderList.get(position);

            String displayText =
                    "Table: " + order.getTableNumber() +
                            "\n" + order.getItems() +
                            " | Status: " + order.getStatus() +
                            " | Total: $" + order.getTotalPrice();

            holder.orderDetailsText.setText(displayText);

            holder.cancelOrderButton.setOnClickListener(v -> {
                String tableNumber = order.getTableNumber();

                // Step 1: Update isOccupied = false for the corresponding table
                db.collection("Tables")
                        .whereEqualTo("tableNumber", tableNumber)
                        .get()
                        .addOnSuccessListener(tableSnapshots -> {
                            for (DocumentSnapshot tableDoc : tableSnapshots) {
                                tableDoc.getReference().update("isOccupied", false);
                            }

                            // Step 2: Delete the corresponding order
                            db.collection("orders")
                                    .whereEqualTo("tableNumber", tableNumber)
                                    .get()
                                    .addOnSuccessListener(orderSnapshots -> {
                                        for (DocumentSnapshot orderDoc : orderSnapshots) {
                                            orderDoc.getReference().delete();
                                        }

                                        // Step 3: Refresh the list
                                        fetchOrders();

                                        Toast.makeText(OrdersActivity.this, "Order cancelled and table freed.", Toast.LENGTH_SHORT).show();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(OrdersActivity.this, "Failed to delete order.", Toast.LENGTH_SHORT).show();
                                    });
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(OrdersActivity.this, "Failed to update table.", Toast.LENGTH_SHORT).show();
                        });
            });


            return convertView;
        }

    }
    private void fetchOrders() {
        db.collection("orders").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                orderList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String tableNumber = document.getString("tableNumber");
                    String status = document.getString("status");
                    Double totalPrice = document.getDouble("totalPrice");

                    // Convert cartItems array to string (e.g., names of items)
                    ArrayList<Map<String, Object>> cartItems = (ArrayList<Map<String, Object>>) document.get("cartItems");
                    StringBuilder itemsBuilder = new StringBuilder();
                    if (cartItems != null) {
                        for (Map<String, Object> item : cartItems) {
                            itemsBuilder.append(item.get("name")).append(" x").append(item.get("quantity")).append(", ");
                        }
                    }

                    if (tableNumber != null && status != null && totalPrice != null) {
                        orderList.add(new OrderModel(tableNumber, itemsBuilder.toString(), status, totalPrice));
                    }
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(OrdersActivity.this, "Failed to reload orders.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}