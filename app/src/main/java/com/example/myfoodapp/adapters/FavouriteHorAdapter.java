//package com.example.myfoodapp.adapters;
//
//import android.app.Activity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.myfoodapp.R;
//import com.example.myfoodapp.models.FavouriteHorModel;
//import com.example.myfoodapp.models.FavouriteVerModel;
//import com.example.myfoodapp.utils.SharedFavouriteList;
//
//import java.util.ArrayList;
//
//public class FavouriteHorAdapter extends RecyclerView.Adapter<FavouriteHorAdapter.ViewHolder> {
//
//    UpdateFavouriteVerticalRec updateVerticalRec;
//    Activity activity;
//    ArrayList<FavouriteHorModel> list;
//    boolean check = true;
//    boolean select = true;
//    int row_index = -1;
//
//    public FavouriteHorAdapter(UpdateFavouriteVerticalRec updateVerticalRec, Activity activity, ArrayList<FavouriteHorModel> list) {
//        this.updateVerticalRec = updateVerticalRec;
//        this.activity = activity;
//        this.list = list;
//    }
//
//    @NonNull
//    @Override
//    public FavouriteHorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new FavouriteHorAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_horizontal_item, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.imageView.setImageResource(list.get(position).getImage());
//        holder.name.setText(list.get(position).getName());
//
//        // Handle the initial filtering and update callback based on position
//        if (check && position >= 0 && position < 4) {
//            ArrayList<FavouriteVerModel> filteredList = getFilteredList(list.get(position).getName());
//            updateVerticalRec.callBack(position, filteredList);
//            check = false;  // Ensures this block is executed only once
//        }
//
//        final int adapterPosition = position;
//
//        holder.cardView.setOnClickListener(v -> {
//            row_index = adapterPosition;
//            notifyDataSetChanged();
//
//            // Filter the list based on the category name of the clicked item
//            ArrayList<FavouriteVerModel> filteredList = getFilteredList(list.get(adapterPosition).getName());
//            updateVerticalRec.callBack(adapterPosition, filteredList);
//
//            // Handle background change for selected position
//            if (select) {
//                if (adapterPosition == 0) {
//                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
//                    select = false;
//                }
//            } else {
//                if (row_index == adapterPosition) {
//                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
//                } else {
//                    holder.cardView.setBackgroundResource(R.drawable.default_bg);
//                }
//            }
//        });
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    // ✅ Filter favorites by category name
//    private ArrayList<FavouriteVerModel> getFilteredList(String category) {
//        ArrayList<FavouriteVerModel> filteredList = new ArrayList<>();
//
//        for (FavouriteVerModel item : SharedFavouriteList.favList) {
//            if (item.getName().toLowerCase().contains(category.toLowerCase())) {
//                filteredList.add(item);
//            }
//        }
//
//        return filteredList;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//        TextView name;
//        CardView cardView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.hor_img);
//            name = itemView.findViewById(R.id.hor_text);
//            cardView = itemView.findViewById(R.id.cardView);
//        }
//    }
//}
