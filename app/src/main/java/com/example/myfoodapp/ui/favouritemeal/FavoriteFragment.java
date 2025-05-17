//package com.example.myfoodapp.ui.favouritemeal;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.myfoodapp.R;
//import com.example.myfoodapp.adapters.FavouriteHorAdapter;
//import com.example.myfoodapp.adapters.FavouriteVerAdapter;
//import com.example.myfoodapp.adapters.UpdateFavouriteVerticalRec;
//import com.example.myfoodapp.models.FavouriteHorModel;
//import com.example.myfoodapp.models.FavouriteVerModel;
//
//import java.util.ArrayList;
//
//public class FavoriteFragment extends Fragment implements UpdateFavouriteVerticalRec {
//
//    private RecyclerView favHorRec, favVerRec;
//    private FavouriteHorAdapter horAdapter;
//    private FavouriteVerAdapter verAdapter;
//
//    private ArrayList<FavouriteHorModel> horList;
//    private ArrayList<FavouriteVerModel> verList;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.favorite_fragment, container, false);
//
//        favHorRec = view.findViewById(R.id.fav_hor_rec);
//        favVerRec = view.findViewById(R.id.fav_ver_rec);
//
//        // Setup Horizontal RecyclerView (categories)
//        horList = new ArrayList<>();
//        horList.add(new FavouriteHorModel(R.drawable.pizza1, "Pizza"));
//        horList.add(new FavouriteHorModel(R.drawable.burger1, "Burger"));
//        horList.add(new FavouriteHorModel(R.drawable.fries1, "Fries"));
//        horList.add(new FavouriteHorModel(R.drawable.sandwich1, "Noodles"));
//
//        horAdapter = new FavouriteHorAdapter(this, getActivity(), horList);
//        favHorRec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        favHorRec.setAdapter(horAdapter);
//
//        // Setup Vertical RecyclerView (favourite meals)
//        verList = new ArrayList<>();
//        verAdapter = new FavouriteVerAdapter(getContext(), verList);
//        favVerRec.setLayoutManager(new LinearLayoutManager(getContext()));
//        favVerRec.setAdapter(verAdapter);
//
//        return view;
//    }
//
//    @Override
//    public void callBack(int position, ArrayList<FavouriteVerModel> list) {
//        verAdapter = new FavouriteVerAdapter(getContext(), list);
//        favVerRec.setAdapter(verAdapter);
//    }
//
//    // Optional method to refresh vertical list
//    public void refreshData() {
//        verAdapter.notifyDataSetChanged();
//    }
//}
