package com.example.myfoodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfoodapp.R;
import com.example.myfoodapp.models.MenuItemModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    Context context;
    List<MenuItemModel> menuList;

    public MenuAdapter(Context context, List<MenuItemModel> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItemModel item = menuList.get(position);
        Glide.with(context)
                .load(item.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);

        //   holder.imageView.setImageResource(item.getImageResId());

        holder.name.setText(item.getName());
        holder.price.setText(item.getPrice());

        // Handle Edit button
        holder.btnEdit.setOnClickListener(v -> {
            // Handle Edit functionality (open a new activity or dialog for editing the item)
        });

        holder.btnDelete.setOnClickListener(v -> {
            String itemPosition = item.getPosition(); // or item.getName() if "position" not used

            // Delete from Firestore
            FirebaseFirestore.getInstance().collection("Menu")
                    .whereEqualTo("position", itemPosition)
                    .get()
                    .addOnSuccessListener(querySnapshot -> {
                        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                            document.getReference().delete();
                        }

                        // After deletion from Firestore, remove from list and refresh UI
                        menuList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(context, "Delete failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        holder.btnEdit.setOnClickListener(v -> {
            showEditDialog(item, position);
        });

    }
    private void showEditDialog(MenuItemModel item, int position) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_item, null);
        builder.setView(dialogView);

        TextView edtName = dialogView.findViewById(R.id.editName);
        TextView edtPrice = dialogView.findViewById(R.id.editPrice);
        TextView edtImage = dialogView.findViewById(R.id.editImageUrl);

        edtName.setText(item.getName());
        edtPrice.setText(item.getPrice());
        edtImage.setText(item.getImageUrl());

        builder.setPositiveButton("Update", (dialog, which) -> {
            String updatedName = edtName.getText().toString();
            String updatedPrice = edtPrice.getText().toString();
            String updatedImage = edtImage.getText().toString();

            // Update locally
            MenuItemModel updatedItem = new MenuItemModel(updatedName, updatedImage, updatedPrice, item.getPosition());

            menuList.set(position, updatedItem);
            notifyItemChanged(position);

            // Update in Firestore
            FirebaseFirestore.getInstance().collection("Menu")
                    .whereEqualTo("position", item.getPosition()) // Or "name", but "position" is safer
                    .get()
                    .addOnSuccessListener(querySnapshot -> {
                        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                            document.getReference().update("name", updatedName,
                                    "price", updatedPrice,
                                    "imageUrl", updatedImage);
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(context, "Update failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }


    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, price;
        Button btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.menu_item_image);
            name = itemView.findViewById(R.id.menu_item_name);
            price = itemView.findViewById(R.id.menu_item_price);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }


    }
}