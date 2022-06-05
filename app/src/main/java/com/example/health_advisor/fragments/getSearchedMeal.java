package com.example.health_advisor.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.health_advisor.R;
import com.example.health_advisor.adapters.meals_adapter_favourite;
import com.example.health_advisor.databases.my_database;
import com.example.health_advisor.interfaces.onRecycleViewItemClickListener;
import com.example.health_advisor.objects.meals;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link getSearchedMeal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class getSearchedMeal extends Fragment {
    RecyclerView searched_RCV;
    ArrayList<meals> newSerchedMeal=new ArrayList<>();
    my_database db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_meal_name = "param1";

    // TODO: Rename and change types of parameters
    private String mmeal_name;

    public getSearchedMeal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param meal_name Parameter 1.
     * @return A new instance of fragment getSearchedMeal.
     */
    // TODO: Rename and change types and number of parameters
    public static getSearchedMeal newInstance(String meal_name) {
        getSearchedMeal fragment = new getSearchedMeal();
        Bundle args = new Bundle();
        args.putString(ARG_meal_name, meal_name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        db=new my_database(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mmeal_name = getArguments().getString(ARG_meal_name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_get_searched_meal, container, false);
        searched_RCV = v.findViewById(R.id.searched_RCV_meals);
        newSerchedMeal=db.searchMealsWithname(mmeal_name);
        searched_RCV.setAdapter(new meals_adapter_favourite(newSerchedMeal, new onRecycleViewItemClickListener() {
            @Override
            public void onItemClickListener(int cardID) {
                Toast.makeText(getActivity(), "ID "+cardID, Toast.LENGTH_SHORT).show();
            }
        }));
        return v;
    }
}