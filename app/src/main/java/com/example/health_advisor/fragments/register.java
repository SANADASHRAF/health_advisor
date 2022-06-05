package com.example.health_advisor.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health_advisor.R;
import com.example.health_advisor.databases.my_database;
import com.example.health_advisor.objects.user;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class register extends Fragment {
    EditText txt_username,txt_Phone,txt_location,txt_password;
    my_database db;
    Uri imageUri;
    onRegisterFragmentClicked listener;
    Button btn_regsiter,btn_choose_img;
    private static final int PICK_IMAGE_REQ_COD = 1;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public register() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment register.
     */
    // TODO: Rename and change types and number of parameters
    public static register newInstance(String param1, String param2) {
        register fragment = new register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof login.onLoginFragmentClcicked){
            listener=(register.onRegisterFragmentClicked) context;
        }else{
            throw new ClassCastException("Your activity doesn't implement onLoginFragmentClcicked interface");
        }
        db=new my_database(context);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_register, container, false);
        btn_regsiter=v.findViewById(R.id.btn_register);
        txt_username=v.findViewById(R.id.regisetr_username);
        btn_choose_img=v.findViewById(R.id.choose_img);
        txt_location=v.findViewById(R.id.register_location);
        txt_Phone=v.findViewById(R.id.register_phone);
        txt_password=v.findViewById(R.id.register_password);
        btn_choose_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent in = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(in,PICK_IMAGE_REQ_COD);

                    }

        });
        btn_regsiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRegisterRegisterClick("btn_register_clciked");
                //send user data to database
                user new_user=new user(txt_username.getText().toString(),
                        txt_password.getText().toString(),txt_Phone.getText().toString(),
                        txt_location.getText().toString(),String.valueOf(imageUri));
                boolean b=db.insertNewUser(new_user);
                if (b==true)
                    Toast.makeText(getContext(), "let's Login", Toast.LENGTH_SHORT).show();
            }
        });

        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQ_COD && resultCode == RESULT_OK){
            if(data != null){
                imageUri = data.getData();
            }
        }
    }

    public interface onRegisterFragmentClicked{
        public void onRegisterRegisterClick(String RegisterClciked);
    }
}