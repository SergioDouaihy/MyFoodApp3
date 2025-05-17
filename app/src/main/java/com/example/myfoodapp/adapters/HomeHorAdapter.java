package com.example.myfoodapp.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapp.R;
import com.example.myfoodapp.models.HomeHorModel;
import com.example.myfoodapp.models.HomeVerModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {

    UpdateHomeVerticalRec updateVerticalRec;
    Activity activity;
    ArrayList<HomeHorModel> list;
    int row_index = -1;

    public HomeHorAdapter(UpdateHomeVerticalRec updateVerticalRec, Activity activity, ArrayList<HomeHorModel> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.imageView.setImageResource(list.get(position).getImage());

        final int adapterPosition = position;

        holder.cardView.setOnClickListener(v -> {
            row_index = adapterPosition;
            notifyDataSetChanged();

            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // Convert adapterPosition to string before comparing with Firestore 'position' field
            db.collection("Menu")
                    .whereEqualTo("position", String.valueOf(adapterPosition)) // <-- Important
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                String name = document.getString("name");
                                String image = document.getString("imageUrl");
                                String price = document.getString("price");

                                homeVerModels.add(new HomeVerModel(image, name, "4.9", "10:00-23:00", "$" + price));
                            }
                            updateVerticalRec.callBack(adapterPosition, homeVerModels);
                        } else {
                            Log.d("Firebase", "Error getting documents for position " + adapterPosition, task.getException());
                        }
                    });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.hor_img);
            name = itemView.findViewById(R.id.hor_text);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
