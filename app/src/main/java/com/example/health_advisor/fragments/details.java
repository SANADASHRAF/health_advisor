package com.example.health_advisor.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.health_advisor.R;
import com.example.health_advisor.R;
import com.example.health_advisor.R;
import com.example.health_advisor.databases.my_database;
import com.example.health_advisor.objects.meals;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class details extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_name = "ARG_name";
    private static final String ARG_details = "ARG_details";
    private static final String ARG_location = "ARG_location";
    private static final String ARG_img = "ARG_img";
    private static final String ARG_rate = "ARG_rate";
    TextView txt_title,txt_details;
    RatingBar rate;
    LinearLayout card_img;
    ImageView details_img;
    my_database db;
    LottieAnimationView LAV_details;

    // TODO: Rename and change types of parameters
    private   String mname;
    private   String mdetails ;
    private   int mimg ;
    private   int mrate ;

    public details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment details.
     */
    // TODO: Rename and change types and number of parameters
    public static details newInstance(String name, String details,int img,int rate) {
        details fragment=new details();
        Bundle args = new Bundle();
        args.putString(ARG_name, name);
        args.putString(ARG_details, details);
        args.putInt(ARG_img, img);
        args.putInt(ARG_rate, rate);
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
            mname = getArguments().getString(ARG_name);
            mdetails = getArguments().getString(ARG_details);
            mimg = getArguments().getInt(ARG_img);
            mrate = getArguments().getInt(ARG_rate);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_details, container, false);
        //assignment
        txt_title=v.findViewById(R.id.details_name);
        txt_details=v.findViewById(R.id.details_details);
        details_img=v.findViewById(R.id.details_img);
        rate=v.findViewById(R.id.details_rate);
        card_img=v.findViewById(R.id.card_img_details);
        LAV_details=v.findViewById(R.id.lotti_details_anim);
        //set values

        card_img.setBackground(getResources().getDrawable(mimg));
        txt_details.setText(mdetails);
        txt_title.setText(mname);
        rate.setRating(mrate);
        details_img.setImageResource(mimg);
        details_img.setVisibility(View.GONE);
        LAV_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LAV_details.playAnimation();

            }
        });
        return v;
    }
}