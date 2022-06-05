package com.example.health_advisor.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health_advisor.R;
import com.example.health_advisor.databases.my_database;
import com.example.health_advisor.objects.user;
import com.example.health_advisor.databases.my_database;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class login extends Fragment {
    onLoginFragmentClcicked listener;
    TextView register;
    EditText txt_username,txt_password;
    Button btn_login;
    my_database db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment login.
     */
    // TODO: Rename and change types and number of parameters
    public static login newInstance(String param1, String param2) {
        login fragment = new login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof onLoginFragmentClcicked){
            listener=(onLoginFragmentClcicked) context;
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
        View v= inflater.inflate(R.layout.fragment_login, container, false);
        register=v.findViewById(R.id.btn_sign_up);
        btn_login=v.findViewById(R.id.btn_login);
        txt_password=v.findViewById(R.id.login_password);
        txt_username=v.findViewById(R.id.login_username);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.getUsername(txt_username.getText().toString());
                if (txt_username.getText().toString().matches("") || txt_password.getText().toString().matches("")){
                    Snackbar.make(getView(), "Write username & password", Snackbar.LENGTH_SHORT).show();
                }else{
                    user u=db.getUsernWithUsername(txt_username.getText().toString());
                    if (txt_username.getText().toString().equals(u.getUsername())   && txt_password.getText().toString().equals(u.getPassword())){
                        Toast.makeText(getActivity(), "Starting app ...", Toast.LENGTH_SHORT).show();
                        listener.signup("button login clicked");
                    }else if (u.getUsername().matches("none" ) || u.getPassword().matches("none")){
                        Snackbar.make(getView(), "Wrong Username OR Password !!", Snackbar.LENGTH_SHORT).show();
                    }
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.signup("button signUP clicked");
            }
        });
        return v;
    }

    public interface onLoginFragmentClcicked{
        void signup(String loginClickedButton);
        public void getUsername(String username);
    }
}