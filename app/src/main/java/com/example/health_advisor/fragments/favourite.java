package com.example.health_advisor.fragments;

import static com.google.android.material.color.MaterialColors.getColor;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.health_advisor.R;
import com.example.health_advisor.adapters.meals_adapter_favourite;
import com.example.health_advisor.databases.my_database;
import com.example.health_advisor.interfaces.onRecycleViewItemClickListener;
import com.example.health_advisor.objects.meals;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link favourite#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favourite extends Fragment {
    onFragmentClciked listener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView RCV_meals;
    ArrayList<meals> meals=new ArrayList<meals>();
    TextView main_txt;
    Animation anim;
    my_database db;
    Context context;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public favourite() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment non_empty_meal.
     */
    // TODO: Rename and change types and number of parameters
    public static favourite newInstance(String param1, String param2) {
        favourite fragment = new favourite();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        context=context;
        db=new my_database(context);
        if (context instanceof onFragmentClciked){
            listener=(onFragmentClciked) context;
        }else{
            throw new ClassCastException("your activity doesn't implement onFragmentClciked interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        meals.clear();
        meals=db.getFavMeals();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_non_empty_meal, container, false);
        RCV_meals=v.findViewById(R.id.RCV_meals);
        main_txt=v.findViewById(R.id.main_text_fav);
        meals_adapter_favourite newAdapter=new meals_adapter_favourite(meals, new onRecycleViewItemClickListener() {
            @Override
            public void onItemClickListener(int cardID) {
                //search in database with card id and passing the data to details fragment
                listener.onFragmentClcik(db.getMealsWithid(cardID));
                db.delteMealWithid(cardID);
            }
        });
        listener.numberOfFavourite(String.valueOf(meals.size()));
        RCV_meals.setHasFixedSize(true);
        RCV_meals.setAdapter(newAdapter);
        anim= AnimationUtils.loadAnimation(getContext(),R.anim.scal);
//        RCV_meals.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
////                main_txt.startAnimation(anim);
//                main_txt.setVisibility(View.GONE);
//                return false;
//            }
//        });
        return v;
    }
    public interface onFragmentClciked{
        void onFragmentClcik(meals newMeal);
        void numberOfFavourite(String num);
    }
}