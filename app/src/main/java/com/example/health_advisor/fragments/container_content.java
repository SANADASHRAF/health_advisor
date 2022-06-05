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
import com.example.health_advisor.adapters.categorie_content_adapter;
import com.example.health_advisor.databases.my_database;
import com.example.health_advisor.objects.meals;
import com.example.health_advisor.interfaces.onRecycleViewItemClickListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link container_content#newInstance} factory method to
 * create an instance of this fragment.
 */
public class container_content extends Fragment {
    my_database db;
    onContentClicked listener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String cat_name = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView RCV_diabets;
    String category_name ="Diabets Food";
    ArrayList<meals> diabits_meals=new ArrayList<meals>();
    // TODO: Rename and change types of parameters
    private String catName;
    private String mParam2;

    public container_content() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment container_content.
     */
    // TODO: Rename and change types and number of parameters
    public static container_content newInstance(String param1, String param2) {
        container_content fragment = new container_content();
        Bundle args = new Bundle();
        args.putString(cat_name, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        db=new my_database(context);
        if (context instanceof onContentClicked)
            listener=(onContentClicked) context;
        else
            throw new ClassCastException("your activity doesn't implement onContentClicked interface");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener =null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            catName = getArguments().getString(cat_name);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if (catName=="diabits_categorie"){
            diabits_meals.clear();
            diabits_meals=db.getMealsWithid(0,6);

        }else if(catName=="fitness_categorie"){
            diabits_meals.clear();
            diabits_meals=db.getMealsWithid(15,22);

        }else if(catName=="hypertensive_categorie"){
            diabits_meals.clear();
            diabits_meals=db.getMealsWithid(7,13);

        }else if(catName=="diet_categorie"){
            diabits_meals.clear();
            diabits_meals=db.getMealsWithid(24,29);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_container_content, container, false);
        //assignments
        RCV_diabets=v.findViewById(R.id.RCV_diabets);
        categorie_content_adapter new_cat_adapter=new categorie_content_adapter(diabits_meals,getContext(),new onRecycleViewItemClickListener() {
            @Override
            public void onItemClickListener(int cardID) {
                Toast.makeText(getActivity(), "ID = "+cardID, Toast.LENGTH_SHORT).show();
                listener.onContentClicked(db.getMealsWithid(cardID));
// علشان نكون فاهمين المشهد كله ي شباااب احنا هنا جوا الفراجمنت بتاعت الكاتجوري
// هنا بقا استخدمنا الانترفيس اللي احنا عملناه للريسيكال ف اننا نجيب الاي دي بتاع العنصر اللي حصل عليك كلك ف الرسيكال دي
// المطلوب بقاا اننا نبعت الاي دي دا للاكتفيتي المين علشان تهندله يعني تجيب الداتا بتاعته م الداتا بيز بعدين تباصيها لفراجمنت الديتلز علشان تعرضها

            }
        });
        RCV_diabets.setHasFixedSize(true);
        RCV_diabets.setAdapter(new_cat_adapter);
        Toast.makeText(getActivity(), "done", Toast.LENGTH_SHORT).show();
        return v;
    }
    public interface onContentClicked{
        void onContentClicked(meals newMeal);
    }
}