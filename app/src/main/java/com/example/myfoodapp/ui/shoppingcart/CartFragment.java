package com.example.myfoodapp.ui.shoppingcart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myfoodapp.R;
import com.example.myfoodapp.models.CartItem;
import com.example.myfoodapp.utils.CartManager;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartFragment extends Fragment {

    private LinearLayout cartItemsContainer;
    private Spinner tableNumberSpinner;
    private FirebaseFirestore db;
    private TextView totalPriceText;
    private TextView orderStatusText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_cart, container, false);
        totalPriceText = root.findViewById(R.id.total_price_text);
        orderStatusText = root.findViewById(R.id.order_status_text);
        cartItemsContainer = root.findViewById(R.id.cart_items_container);
        tableNumberSpinner = root.findViewById(R.id.table_spinner);

        db = FirebaseFirestore.getInstance();

        db.collection("Tables")
                .whereEqualTo("isOccupied", false)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<String> availableTables = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : task.getResult()) {
                            String tableNumber = doc.getString("tableNumber");
                            if (tableNumber != null) {
                                availableTables.add(tableNumber);
                            }
                        }

                        if (availableTables.isEmpty()) {
                            Toast.makeText(getContext(), "No available tables", Toast.LENGTH_SHORT).show();
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                getContext(),
                                android.R.layout.simple_spinner_item,
                                availableTables
                        );
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        tableNumberSpinner.setAdapter(adapter);

                    } else {
                        Toast.makeText(getContext(), "Failed to load tables", Toast.LENGTH_SHORT).show();
                    }
                });

        CartManager.loadCart(getContext());

        Button checkoutButton = root.findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(v -> handleCheckout());

        displayCartItems();

        return root;
    }

    private void updateTableOccupancy(String tableNumber, boolean isOccupied) {
        db.collection("Tables")
                .whereEqualTo("tableNumber", tableNumber)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                        db.collection("Tables")
                                .document(doc.getId())
                                .update("isOccupied", isOccupied);
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Failed to update table status: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

    private void displayCartItems() {
        cartItemsContainer.removeAllViews();

        List<CartItem> items = CartManager.getCartItems();

//        if (items.isEmpty()) {
//            orderStatusText.setVisibility(View.GONE);
//        }

        for (CartItem item : new ArrayList<>(items)) {
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_cart, cartItemsContainer, false);

            ImageView itemImage = itemView.findViewById(R.id.cart_item_image);
            TextView itemName = itemView.findViewById(R.id.cart_item_name);
            TextView itemPrice = itemView.findViewById(R.id.cart_item_price);
            TextView itemQuantity = itemView.findViewById(R.id.cart_item_quantity);
            ImageView deleteIcon = itemView.findViewById(R.id.delete_item);

            String imageUrl = item.imageUrl;
            if (!imageUrl.endsWith(".jpg") && !imageUrl.endsWith(".png")) {
                imageUrl += ".jpg";
            }

            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .into(itemImage);

            itemName.setText(item.name);
            itemPrice.setText("Price: " + item.price);
            itemQuantity.setText("Quantity: " + item.quantity);

            deleteIcon.setOnClickListener(v -> {
                CartManager.removeItem(getContext(), item);
                displayCartItems();
            });

            cartItemsContainer.addView(itemView);
        }

        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double total = CartManager.getTotalPrice();
        totalPriceText.setText("Total: $" + String.format("%.2f", total));
    }

    private void handleCheckout() {
        String selectedTable = (String) tableNumberSpinner.getSelectedItem();

        if (selectedTable == null || selectedTable.isEmpty()) {
            Toast.makeText(getContext(), "Please select a table.", Toast.LENGTH_SHORT).show();
            return;
        }

        List<CartItem> cart = CartManager.getCartItems();

        if (cart.isEmpty()) {
            Toast.makeText(getContext(), "Your cart is empty.", Toast.LENGTH_SHORT).show();
            return;
        }

        double totalPrice = CartManager.getTotalPrice();

        Map<String, Object> orderData = new HashMap<>();
        orderData.put("tableNumber", selectedTable);
        orderData.put("totalPrice", totalPrice);
        orderData.put("status", "Your order is being prepared");

        List<Map<String, Object>> cartItemsList = new ArrayList<>();
        for (CartItem item : cart) {
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("name", item.name);
            itemMap.put("price", item.price);
            itemMap.put("imageUrl", item.imageUrl);
            itemMap.put("quantity", item.quantity);
            cartItemsList.add(itemMap);
        }
        orderData.put("cartItems", cartItemsList);

        db.collection("orders")
                .add(orderData)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getContext(), "Order placed successfully!", Toast.LENGTH_SHORT).show();

                    // Show status message
                    orderStatusText.setText("Your order is being prepared");
                    orderStatusText.setVisibility(View.VISIBLE);

                    updateTableOccupancy(selectedTable, true);

                    // Clear cart and refresh UI
                    CartManager.clearCart(getContext());
                    displayCartItems();
                    updateTotalPrice();

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Failed to place order: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }
}
