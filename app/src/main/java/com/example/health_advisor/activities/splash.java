package com.example.health_advisor.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.health_advisor.R;
import com.example.health_advisor.fragments.login;
import com.example.health_advisor.fragments.profile;
import com.example.health_advisor.fragments.register;

public class splash extends AppCompatActivity implements login.onLoginFragmentClcicked, register.onRegisterFragmentClicked {
    TextView app_name;
    ImageView app_logo;
    private static final int PERMISSION_REQ_COD = 1;
    ImageView app_background;
    TextView sp_title_login;
    private static String userName;
    LottieAnimationView LAV;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //get permison
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQ_COD);
        }
        //end perminion
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        sp_title_login=findViewById(R.id.sp_title_login);
        app_name=findViewById(R.id.app_name_splash);
        app_background=findViewById(R.id.app_background_splash);
        app_logo=findViewById(R.id.app_logo_splash);
        anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        LAV=findViewById(R.id.lotti_splash);
        app_name.animate().translationY(1600).setDuration(1000).setStartDelay(1500);
        app_background.animate().translationY(-1200).setDuration(1000).setStartDelay(1500);
        app_logo.animate().translationY(1600).setDuration(1000).setStartDelay(1500);
        LAV.animate().translationY(-1600).setDuration(1000).setStartDelay(1500);
//        Intent i =new Intent(getApplicationContext(),MainActivity.class);
//        startActivity(i);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FragmentManager fm=getSupportFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.setCustomAnimations(R.anim.enter,R.anim.exit);
                        login det=new login();
                        ft.replace(R.id.fragment_login_container,det);
                        ft.commit();
                        sp_title_login.setAnimation(anim);
                        sp_title_login.setVisibility(View.VISIBLE);
                    }
                });
            }
        }).start();


    }
    //handle signUp fragment actions
    @Override
    public void signup(String btn_signUP_clcik) {
        switch (btn_signUP_clcik){
            case "button signUP clicked":
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft =fm.beginTransaction();
                register r=new register();
                ft.setCustomAnimations(R.anim.enter,R.anim.exit);
                ft.replace(R.id.fragment_login_container,r);
                ft.commit();
                sp_title_login.setText("Register");
                break;
            case "button login clicked":
                //check validation of user information in database

                //validation is validate
                Intent in=new Intent(getApplicationContext(), MainActivity.class);
                in.putExtra("username",userName);
                Toast.makeText(this, "user = "+userName, Toast.LENGTH_SHORT).show();
                overridePendingTransition(R.anim.pop_enter,R.anim.pop_exit);
                startActivity(in);
                break;
        }
    }

    @Override
    public void getUsername(String username) {
        Toast.makeText(this, "user "+username, Toast.LENGTH_SHORT).show();
        this.userName=username;

    }

    @Override
    public void onRegisterRegisterClick(String btn_register_clcik) {
        switch (btn_register_clcik){
            case "btn_register_clciked":
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft =fm.beginTransaction();
                login r=new login();
                ft.setCustomAnimations(R.anim.enter,R.anim.exit);
                ft.replace(R.id.fragment_login_container,r);
                ft.commit();
                sp_title_login.setText("Login");
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQ_COD:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    //تم الحصول علي
                    Toast.makeText(this, "get Permission", Toast.LENGTH_SHORT).show();
                }else{

                }
        }
    }

}