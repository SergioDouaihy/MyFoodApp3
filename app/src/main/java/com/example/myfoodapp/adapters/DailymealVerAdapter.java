//package com.example.myfoodapp.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageSwitcher;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.myfoodapp.R;
//import com.example.myfoodapp.models.DailymealVerModel;
//
//import java.util.List;
//
//public class DailymealVerAdapter extends RecyclerView.Adapter<DailymealVerAdapter.ViewHolder> {
//    @NonNull
//    Context context;
//    List<DailymealVerModel> list;
//
//    public DailymealVerAdapter(@NonNull Context context, List<DailymealVerModel> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//    @Override
//    public DailymealVerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.daily_meal_vertical_item,parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        DailymealVerModel model = list.get(position);
//        holder.imageView.setImageResource(model.getImage());
//        holder.name.setText(model.getName());
//        holder.rating.setText(model.getRating());
//        holder.price.setText(model.getPrice());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//        TextView name, rating, price;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.ver_img);
//            name = itemView.findViewById(R.id.name);
//            rating = itemView.findViewById(R.id.rating);
//            price = itemView.findViewById(R.id.price);
//
// }
//}
//}
