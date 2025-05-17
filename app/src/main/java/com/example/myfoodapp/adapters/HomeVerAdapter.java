package com.example.myfoodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfoodapp.R;
import com.example.myfoodapp.models.CartItem;
import com.example.myfoodapp.models.HomeVerModel;
import com.example.myfoodapp.utils.CartManager;

import java.util.List;

public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder> {

    Context context;
    List<HomeVerModel> list;

    public HomeVerAdapter(Context context, List<HomeVerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeVerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_verticale_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeVerAdapter.ViewHolder holder, int position) {
        HomeVerModel model = list.get(position);
        // Use Glide to load the image from the URL
        Glide.with(context).load(model.getImage()).into(holder.imageView);




        holder.name.setText(model.getName());
        holder.rating.setText(model.getRating());
        holder.price.setText("Price: " + model.getPrice());  // This will now show the formatted price

        holder.addToCartBtn.setOnClickListener(v -> {
            // Create CartItem from HomeVerModel
            CartItem cartItem = new CartItem(model.getName(), model.getPrice(), model.getImage());

            // Add to CartManager and save in SharedPreferences
            CartManager.addItem(context, cartItem);

            Toast.makeText(context, model.getName() + " added to Cart", Toast.LENGTH_SHORT).show();
        });



        // Add to Favorite click
//        holder.favIcon.setOnClickListener(v -> {
//            Toast.makeText(context, model.getName() + " added to Favorites", Toast.LENGTH_SHORT).show();
//        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView, addToCartBtn, favIcon;
        TextView name, rating, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ver_img);
            addToCartBtn = itemView.findViewById(R.id.add_to_cart_btn);
//          favIcon = itemView.findViewById(R.id.fav_icon);

            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);
        }
    }
}
