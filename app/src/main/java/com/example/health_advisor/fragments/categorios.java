package com.example.health_advisor.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.health_advisor.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link categorios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class categorios extends Fragment {
    private onFragmentClicklistener listener;
    LinearLayout btn_cat_diabits;
    LinearLayout btn_cat_hypertensive;
    LinearLayout btn_cat_fitness;
    LinearLayout btn_cat_diet;
    String Clickedcategorie="null";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public categorios() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment categorios_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static categorios newInstance(String param1, String param2) {
        categorios fragment = new categorios();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof onFragmentClicklistener)
            listener=(onFragmentClicklistener) context;
        else
            throw new ClassCastException("your activity doesn't implement onFragmentClicklistener !!");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_categorios_fragment, container, false);
        View RCV_item_layout= inflater.inflate(R.layout.categorie_content_layout, container, false);
        btn_cat_diabits=v.findViewById(R.id.btn_categoriy_diabits);
        btn_cat_fitness=v.findViewById(R.id.btn_categoriy_fitness);
        btn_cat_hypertensive=v.findViewById(R.id.btn_categoriy_Hypertunsive);
        btn_cat_diet= v.findViewById(R.id.btn_categoriy_diet);
        btn_cat_diabits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFragmentItemClick("diabits_categorie");
            }
        });
        btn_cat_fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFragmentItemClick("fitness_categorie");
            }
        });
        btn_cat_hypertensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFragmentItemClick("hypertensive_categorie");
            }
        });
        btn_cat_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFragmentItemClick("diet_categorie");
            }
        });
        return v;
    }

    //create listener to send the name of clicked categorie to the main actvity
    public interface onFragmentClicklistener{
        void onFragmentItemClick(String Clickedcategorie);
    }
}