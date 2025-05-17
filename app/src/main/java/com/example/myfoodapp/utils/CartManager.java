package com.example.myfoodapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.myfoodapp.models.CartItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static List<CartItem> cartItems = new ArrayList<>();

    private static final String PREFS_NAME = "cart_prefs";
    private static final String CART_KEY = "cart_items";

    public static List<CartItem> getCartItems() {
        return cartItems;
    }

    public static void setCartItems(List<CartItem> items) {
        cartItems = items;
    }

    public static void addItem(Context context, CartItem newItem) {
        boolean itemExists = false;

        for (CartItem item : cartItems) {
            if (item.name.equals(newItem.name)) {
                item.quantity += 1;
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            newItem.quantity = 1;
            cartItems.add(newItem);
        }

        saveCart(context);
    }

    public static double getTotalPrice() {
        double total = 0.0;
        for (CartItem item : getCartItems()) {
            try {
                // Remove any non-digit and non-dot characters like '$' and spaces
                String cleanedPrice = item.price.replaceAll("[^\\d.]", "").trim();
                double price = Double.parseDouble(cleanedPrice);
                total += price * item.quantity;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return total;
    }

    public static void removeItem(Context context, CartItem targetItem) {
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            if (item.name.equals(targetItem.name)) {
                if (item.quantity > 1) {
                    item.quantity -= 1;
                } else {
                    cartItems.remove(i);
                }
                break;
            }
        }
        saveCart(context);
    }

    public static void saveCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(cartItems);
        editor.putString(CART_KEY, json);
        editor.apply();
    }

    public static void loadCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(CART_KEY, null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<CartItem>>(){}.getType();
            cartItems = gson.fromJson(json, type);
        } else {
            cartItems = new ArrayList<>();
        }
    }
    public static void clearCart(Context context) {
        cartItems.clear();
        saveCart(context);
    }

}
