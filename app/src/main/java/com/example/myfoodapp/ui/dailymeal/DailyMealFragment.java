//package com.example.myfoodapp.ui.dailymeal;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//
//import com.example.myfoodapp.R;
//
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.myfoodapp.R;
//import com.example.myfoodapp.adapters.DailymealHorAdapter;
//import com.example.myfoodapp.adapters.DailymealVerAdapter;
//import com.example.myfoodapp.adapters.DailymealHorAdapter;
//import com.example.myfoodapp.adapters.DailymealVerAdapter;
//import com.example.myfoodapp.models.DailymealHorModel;
//import com.example.myfoodapp.models.DailymealVerModel;
//import com.example.myfoodapp.models.DailymealHorModel;
//import com.example.myfoodapp.models.DailymealVerModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class DailyMealFragment extends Fragment {
//    RecyclerView dailyMealVerRec, dailyMealHorRec;
//    List<DailymealVerModel> dailyMealVerModelList;
//    DailymealHorAdapter dailyMealHorAdapter;
//    List<DailymealHorModel> dailyMealHorModelList;
//    DailymealVerAdapter dailyMealVerAdapter;
//
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.daily_meal_fragment,container,false);
//        dailyMealVerRec = root.findViewById(R.id.ver_rec);
//        dailyMealHorRec = root.findViewById(R.id.hor_rec);
//        dailyMealHorModelList = new ArrayList<>();
//        dailyMealHorModelList.add(new DailymealHorModel(R.drawable.pizza1, "Pizza"));
//        dailyMealHorModelList.add(new DailymealHorModel(R.drawable.burger1, "Hamburger"));
//        dailyMealHorModelList.add(new DailymealHorModel(R.drawable.fries1, "Fries"));
//        dailyMealHorModelList.add(new DailymealHorModel(R.drawable.sandwich1, "Sandwich"));
//        dailyMealHorModelList.add(new DailymealHorModel(R.drawable.sandwich1, "Sandwich"));
//        dailyMealHorModelList.add(new DailymealHorModel(R.drawable.sandwich1, "Sandwich"));
//        dailyMealHorModelList.add(new DailymealHorModel(R.drawable.sandwich1, "Sandwich"));
//        dailyMealHorModelList.add(new DailymealHorModel(R.drawable.sandwich1, "Sandwich"));
//        dailyMealHorModelList.add(new DailymealHorModel(R.drawable.sandwich1, "Sandwich"));
//
//        dailyMealHorAdapter = new DailymealHorAdapter(getActivity(), dailyMealHorModelList);
//        dailyMealHorRec.setAdapter(dailyMealHorAdapter);
//        dailyMealHorRec.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        dailyMealHorRec.setHasFixedSize(true);
//        dailyMealHorRec.setNestedScrollingEnabled(true);
//
//
//
//        dailyMealVerModelList=new ArrayList<>();
//        dailyMealVerModelList.add(new DailymealVerModel(R.drawable.pizza1,"Pizza","10.00-23:00","4.9","Min - $45"));
//        dailyMealVerModelList.add(new DailymealVerModel(R.drawable.pizza1,"Pizza","10.00-23:00","4.9","Min - $45"));
//        dailyMealVerModelList.add(new DailymealVerModel(R.drawable.pizza1,"Pizza","10.00-23:00","4.9","Min - $45"));
//        dailyMealVerModelList.add(new DailymealVerModel(R.drawable.pizza1,"Pizza","10.00-23:00","4.9","Min - $45"));
//        dailyMealVerModelList.add(new DailymealVerModel(R.drawable.pizza1,"Pizza","10.00-23:00","4.9","Min - $45"));
//        dailyMealVerModelList.add(new DailymealVerModel(R.drawable.pizza1,"Pizza","10.00-23:00","4.9","Min - $45"));
//        dailyMealVerModelList.add(new DailymealVerModel(R.drawable.pizza1,"Pizza","10.00-23:00","4.9","Min - $45"));
//        dailyMealVerModelList.add(new DailymealVerModel(R.drawable.pizza1,"Pizza","10.00-23:00","4.9","Min - $45"));
//
//        dailyMealVerAdapter=new DailymealVerAdapter(getActivity(),dailyMealVerModelList);
//        dailyMealVerRec.setAdapter(dailyMealVerAdapter);
//        dailyMealVerRec.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        dailyMealVerRec.setHasFixedSize(true);
//        dailyMealVerRec.setNestedScrollingEnabled(false);
//
//        return root;
//}
//
//}