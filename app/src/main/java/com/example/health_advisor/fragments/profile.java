package com.example.health_advisor.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health_advisor.R;
import com.example.health_advisor.activities.splash;
import com.example.health_advisor.databases.my_database;
import com.example.health_advisor.objects.user;

import java.net.URI;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profile extends Fragment {
    Button btn_logout;
    ImageView profile_img;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_username = "ARG_username";
    private static final String ARG_numOFFav = "ARG_numOFFav";
    TextView txt_username,txt_phone,txt_location,txt_fav_num,txt_location_2,txt_username_2;
    Animation anim_enter;

    // TODO: Rename and change types of parameters
    private  String musername;
    private  String mnumOFFav;
    my_database db;

    public profile() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static profile newInstance(String username,String numOFFav) {
        profile fragment = new profile();
        Bundle args = new Bundle();
        args.putString(ARG_username, username);
        args.putString(ARG_numOFFav,numOFFav);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            musername = getArguments().getString(ARG_username);
            mnumOFFav = getArguments().getString(ARG_numOFFav);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        db=new my_database(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.profile, container, false);
        //get data from database
        Toast.makeText(getActivity(),"username "+musername,Toast.LENGTH_SHORT).show();
        user getUserinfo=db.getUsernWithUsername(musername);
        //assignment
        txt_location=v.findViewById(R.id.location_txt_profile);
        txt_location_2=v.findViewById(R.id.location_txt_profile_2);
        txt_phone=v.findViewById(R.id.phone_txt_profile);
        txt_username=v.findViewById(R.id.fullname_txt_profile);
        profile_img=v.findViewById(R.id.profile_image);
        txt_username_2=v.findViewById(R.id.username_field_profile);
        btn_logout=v.findViewById(R.id.btn_profile_logout);
        txt_fav_num=v.findViewById(R.id.ui_profile_favourite);
        //set values
        txt_username_2.setText(getUserinfo.getUsername());
        txt_fav_num.setText(mnumOFFav);
        txt_location.setText(getUserinfo.getLocation());
        txt_phone.setText(getUserinfo.getPhone());
        txt_location_2.setText(getUserinfo.getLocation());
        txt_username.setText(getUserinfo.getUsername());
        profile_img.setImageURI(Uri.parse(getUserinfo.getImg_uri()));
//        profile_img.setImageResource(mimg);
        Toast.makeText(getActivity(), "name = " +musername, Toast.LENGTH_SHORT).show();
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getActivity().getApplicationContext(), splash.class);
                getActivity().overridePendingTransition(R.anim.exit,R.anim.enter);
                startActivity(in);
            }
        });
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}