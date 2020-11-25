package utt.cntt.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Home_Fragement extends Fragment {
    Food_Adaper foodAdaper;
    ArrayList<Food> foods;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragement, container, false);
        initView(view);
        return view;
    }
    private  void initView(View v){
        recyclerView = v.findViewById(R.id.foodslist);
        foods = new ArrayList<>();
        for(int i = 0 ; i < 50; i++){
            foods.add(new Food(R.drawable.china, "china"));
        }
        foodAdaper = new Food_Adaper( foods, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(foodAdaper);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
