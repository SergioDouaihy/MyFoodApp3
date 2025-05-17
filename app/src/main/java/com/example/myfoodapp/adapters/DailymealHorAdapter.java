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
//import com.example.myfoodapp.models.DailymealHorModel;
//import com.example.myfoodapp.models.HomeHorModel;
//
//import java.util.List;
//
//public class DailymealHorAdapter extends RecyclerView.Adapter<DailymealHorAdapter.ViewHolder> {
//    @NonNull
//
//    Context context;
//    List<DailymealHorModel> list;
//
//    public DailymealHorAdapter(@NonNull Context context, List<DailymealHorModel> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//    @Override
//    public DailymealHorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_meal_horizontal_item,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        holder.imageView.setImageResource(list.get(position).getImage());
//        holder.name.setText(list.get(position).getName());
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
//        TextView name;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.hor_img);
//            name = itemView.findViewById(R.id.hor_text);
//
// }
//}
//}
