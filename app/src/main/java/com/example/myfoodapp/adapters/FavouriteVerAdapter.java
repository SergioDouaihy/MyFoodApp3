//package com.example.myfoodapp.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.myfoodapp.R;
//import com.example.myfoodapp.models.FavouriteVerModel;
//import com.example.myfoodapp.models.HomeVerModel;
//
//import java.util.ArrayList;
//
//public class FavouriteVerAdapter extends RecyclerView.Adapter<FavouriteVerAdapter.ViewHolder> {
//    Context context;
//    ArrayList<FavouriteVerModel> list;
//
//    public FavouriteVerAdapter(Context context, ArrayList<FavouriteVerModel> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//    @NonNull
//    @Override
//    public FavouriteVerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.favourite_verticale_item, parent, false);
//        return new FavouriteVerAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull FavouriteVerAdapter.ViewHolder holder, int position) {
//        FavouriteVerModel model = list.get(position);
//
//        holder.imageView.setImageResource(model.getImage());   // ver_img
//        holder.name.setText(model.getName());
//        holder.rating.setText(model.getRating());
//        holder.price.setText(model.getPrice());
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView imageView;
//        TextView name, rating, price;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            imageView = itemView.findViewById(R.id.ver_img);
//            name = itemView.findViewById(R.id.name);
//            rating = itemView.findViewById(R.id.rating);
//            price = itemView.findViewById(R.id.price);
//        }
//    }
//}
