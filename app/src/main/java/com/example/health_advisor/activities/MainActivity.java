package com.example.health_advisor.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health_advisor.R;
import com.example.health_advisor.databases.my_database;
import com.example.health_advisor.fragments.container_content;
import com.example.health_advisor.fragments.categorios;
import com.example.health_advisor.fragments.details;
import com.example.health_advisor.fragments.empty_favourite;
import com.example.health_advisor.fragments.favourite;
import com.example.health_advisor.fragments.getSearchedMeal;
import com.example.health_advisor.fragments.login;
import com.example.health_advisor.fragments.profile;
import com.example.health_advisor.objects.meals;

public class MainActivity extends AppCompatActivity implements categorios.onFragmentClicklistener,favourite.onFragmentClciked,container_content.onContentClicked{
    ImageView btn_main_favorite,btn_main_home,btn_main_profile;
    TextView main_txt,para_txt;
    String username;
    String num;
    LinearLayout main_footer,main_activity;
    Animation anim;
    my_database db=new my_database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        if (db.getMeals().size()==0){
            //insert data to database
            db.insertNewMeal(new meals(1,getString(R.string.Beans_DI),R.drawable.beans,getString(R.string.Beans_details_DI),4,getString(R.string.diabetics_food),getColor(R.color.first_card),"no"));
            db.insertNewMeal(new meals(2,getString(R.string.nuts_DI),R.drawable.nuts,getString(R.string.nuts_details_DI),2,getString(R.string.diabetics_food),getColor(R.color.first_card),"no"));
            db.insertNewMeal(new meals(3,getString(R.string.fruit_DI),R.drawable.fruits,getString(R.string.fruit_details_DI),5,getString(R.string.diabetics_food),getColor(R.color.first_card),"no"));
            db.insertNewMeal(new meals(4,getString(R.string.fatty_fish_DI),R.drawable.fatty_fish,getString(R.string.fatty_fish_details_DI),1,getString(R.string.diabetics_food),getColor(R.color.first_card),"no"));
            db.insertNewMeal(new meals(5,getString(R.string.dark_leafy_greens_DI),R.drawable.dark_leafy_greens,getString(R.string.dark_leafy_greens_details_DI),4,getString(R.string.diabetics_food),getColor(R.color.first_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.Whole_grains_DI),R.drawable.whole_grains,getString(R.string.Whole_grainsdetails_DI),2,getString(R.string.diabetics_food),getColor(R.color.first_card),"no"));


            db.insertNewMeal(new meals(6,getString(R.string.Leafy_vegetables_HP),R.drawable.leafy_vegetables,getString(R.string.Leafy_vegetables_Details_HP),2,getString(R.string.hypertensives_food),getColor(R.color.second_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.Raspberry_and_strawberry_HP),R.drawable.raspberry_and_strawberry,getString(R.string.Raspberry_and_strawberry_details_HP),2,getString(R.string.hypertensives_food),getColor(R.color.second_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.Seeds_HP),R.drawable.seeds,getString(R.string.Seeds_details_HP),2,getString(R.string.hypertensives_food),getColor(R.color.second_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.oats_HP),R.drawable.oats,getString(R.string.oats_details_HP),2,getString(R.string.hypertensives_food),getColor(R.color.second_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.banana_HP),R.drawable.the_banana,getString(R.string.banana_details_HP),2,getString(R.string.hypertensives_food),getColor(R.color.second_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.olive_oil_HP),R.drawable.olive_oil,getString(R.string.red_beet_details_HP),2,getString(R.string.hypertensives_food),getColor(R.color.second_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.red_beet_HP),R.drawable.red_beet,getString(R.string.red_beet_details_HP),2,getString(R.string.hypertensives_food),getColor(R.color.second_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.pistachio_HP),R.drawable.pistachio,getString(R.string.pistachio_details_HP),2,getString(R.string.hypertensives_food),getColor(R.color.second_card),"no"));



            db.insertNewMeal(new meals(6,getString(R.string.cottage_cheese_DT),R.drawable.cottage_cheese,getString(R.string.cottage_cheese_details_DT),2,getString(R.string.diet_food),getColor(R.color.third_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.Salmon_DT),R.drawable.salmon,getString(R.string.Salmon_details_DT),2,getString(R.string.diet_food),getColor(R.color.third_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.boiled_potatoes_DT),R.drawable.boiled_potatoes,getString(R.string.boiled_potatoes_details_DT),2,getString(R.string.diet_food),getColor(R.color.third_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.Beans_DI),R.drawable.beans,getString(R.string.Beans_details_DI),2,getString(R.string.diet_food),getColor(R.color.third_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.pistachio_HP),R.drawable.pistachio,getString(R.string.pistachio_details_HP),2,getString(R.string.diet_food),getColor(R.color.third_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.soups_DT),R.drawable.soups,getString(R.string.soups_details_DT),2,getString(R.string.diet_food),getColor(R.color.third_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.Seeds_HP),R.drawable.seeds,getString(R.string.Seeds_details_HP),2,getString(R.string.diet_food),getColor(R.color.third_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.Avocados_DT),R.drawable.avocados,getString(R.string.Avocados_details_DT),2,getString(R.string.diet_food),getColor(R.color.third_card),"no"));

            db.insertNewMeal(new meals(6,getString(R.string.Eggs_FT),R.drawable.eggs,getString(R.string.Eggs_FT),2,getString(R.string.fitness_food),getColor(R.color.fourth_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.Chicken_breast_FT),R.drawable.chicken_breast,getString(R.string.Chicken_breast_details_FT),2,getString(R.string.fitness_food),getColor(R.color.fourth_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.fishs_FT),R.drawable.fish,getString(R.string.fishs_details_FT),2,getString(R.string.fitness_food),getColor(R.color.fourth_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.Red_meat_FT),R.drawable.red_meat,getString(R.string.Red_meat_details_FT),2,getString(R.string.fitness_food),getColor(R.color.fourth_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.Peanuts_FT),R.drawable.peanuts,getString(R.string.Peanuts_details_FT),2,getString(R.string.fitness_food),getColor(R.color.fourth_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.lentils_FT),R.drawable.lentils,getString(R.string.lentils_details_FT),2,getString(R.string.fitness_food),getColor(R.color.fourth_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.soy_FT),R.drawable.soy,getString(R.string.soy_details_FT),2,getString(R.string.fitness_food),getColor(R.color.fourth_card),"no"));
            db.insertNewMeal(new meals(6,getString(R.string.Dairy_products_FT),R.drawable.dairy_products_,getString(R.string.Dairy_products_Details_FT),2,getString(R.string.fitness_food),getColor(R.color.fourth_card),"no"));
           boolean b= db.insertNewMeal(new meals(6,getString(R.string.Green_Beans_FT),R.drawable.green_beans,getString(R.string.Green_Beans_details_FT),2,getString(R.string.fitness_food),getColor(R.color.fourth_card),"no"));

            if (b==true)
                Toast.makeText(this, "كله تمم ي كبيير", Toast.LENGTH_SHORT).show();
        }

        //assign all variable
        btn_main_favorite=findViewById(R.id.btn_main_favorite);
        btn_main_home=findViewById(R.id.btn_main_home);
        btn_main_profile=findViewById(R.id.btn_main_profile);
        main_txt=findViewById(R.id.main_text);
        para_txt=findViewById(R.id.para_text);
        main_footer=findViewById(R.id.main_footer);
        main_activity=findViewById(R.id.main_activity);
        // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        //prepare action bar apperance
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor("#402C4F"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.ic_healthy);
        actionBar.setTitle("categories");
        //get categories fragment
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        categorios f=new categorios();
        ft.replace(R.id.categories_fr_container,f);
        ft.commit();
        //get favourite fragment
        btn_main_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //handle buttons style in pressed
                btn_main_favorite.setImageResource(R.drawable.ic_favorite_pressed);
                btn_main_profile.setImageResource(R.drawable.ic_profile);
                btn_main_home.setImageResource(R.drawable.ic_home);
                main_txt.setVisibility(View.GONE);
                para_txt.setVisibility(View.GONE);
                actionBar.setTitle("Favourites");
                //active them
                ColorDrawable colorDrawable1= new ColorDrawable(Color.parseColor("#C3AED6"));
                main_footer.setBackground(colorDrawable1);
                main_activity.setBackgroundColor(Color.parseColor("#C3AED6"));
                getSupportActionBar().setBackgroundDrawable(colorDrawable1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.parseColor("#C3AED6"));
                }
                //bring fragment
                FragmentManager fm= getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                //add animation to the fragment
                ft.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                //check if favourite is empty.. to bring suitable ragment
                if(db.getFavMeals().size()==0){
                    empty_favourite mef=new empty_favourite();
                    ft.replace(R.id.categories_fr_container,mef);
                    ft.commit();
                }else{
                    favourite mef=new favourite();
                    ft.replace(R.id.categories_fr_container,mef);
                    ft.commit();
                }



            }
        });
        //get Home fragment
        btn_main_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backHome();
            }
        });
        btn_main_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //handle buttons style in pressed
                btn_main_favorite.setImageResource(R.drawable.ic_favorite);
                btn_main_profile.setImageResource(R.drawable.ic_profile_preesed);
                btn_main_home.setImageResource(R.drawable.ic_home);
                main_txt.setVisibility(View.GONE);
                para_txt.setVisibility(View.GONE);
                actionBar.setTitle("Profile");
                //active them
                ColorDrawable colorDrawable1= new ColorDrawable(Color.parseColor("#219F94"));
                main_footer.setBackground(colorDrawable1);
                main_activity.setBackgroundColor(Color.parseColor("#219F94"));
                getSupportActionBar().setBackgroundDrawable(colorDrawable1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.parseColor("#219F94"));
                }
                //bring fragment
                FragmentManager fm= getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                //add animation to the fragment
                ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                Toast.makeText(MainActivity.this, "user............"+getIntent().getExtras().getString("username"), Toast.LENGTH_LONG).show();
                profile mef=new profile().newInstance(getIntent().getExtras().getString("username"),num);
                ft.replace(R.id.categories_fr_container,mef);
                ft.commit();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            backHome();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

//        if (searchView ==null)
//            Toast.makeText(this, "المشكله هناا ي زعييم", Toast.LENGTH_SHORT).show();

        searchView.setQueryHint("Type Food Name");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //bring fragment
                FragmentManager fm= getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                //add animation to the fragment
                ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                getSearchedMeal sm=new getSearchedMeal().newInstance(query);
                ft.replace(R.id.categories_fr_container,sm);
                ft.commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, newText+"...", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    public void backHome(){
        //handle buttons style in pressed
        btn_main_favorite.setImageResource(R.drawable.ic_favorite);
        btn_main_profile.setImageResource(R.drawable.ic_profile);
        btn_main_home.setImageResource(R.drawable.ic_home_pressed);
        main_txt.setVisibility(View.VISIBLE);
        para_txt.setVisibility(View.VISIBLE);
        //prepare text animation
        anim=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scal);
        main_txt.startAnimation(anim);
        para_txt.setAnimation(anim);
        getSupportActionBar().setTitle("Categories");
        //reset them
        ColorDrawable colorDrawable1= new ColorDrawable(Color.parseColor("#402C4F"));
        main_footer.setBackground(colorDrawable1);
        main_activity.setBackgroundColor(Color.parseColor("#402C4F"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#402C4F"));
        }
        //bring fragment
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
        categorios f=new categorios();
        ft.replace(R.id.categories_fr_container,f);
        ft.commit();
    }
    //change menu to the menu that we had created

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    public void changeTitleOnActionbar(ActionBar actionBar,String title,String color){
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor(color));
        actionBar.setTitle(title);
        actionBar.setBackgroundDrawable(colorDrawable);
        main_activity.setBackground(colorDrawable);
        main_footer.setBackground(colorDrawable);
    }

    @Override
    public void onFragmentItemClick(String Clickedcategorie) {
        switch (Clickedcategorie){
            case "diabits_categorie":
                FragmentManager fm= getSupportFragmentManager();
                getSupportActionBar().setTitle("Diabits Food");
                ColorDrawable colorDrawable1= new ColorDrawable(Color.parseColor("#B5EAEA"));
                main_footer.setBackground(colorDrawable1);
                main_activity.setBackgroundColor(Color.parseColor("#B5EAEA"));
                getSupportActionBar().setBackgroundDrawable(colorDrawable1);
                FragmentTransaction ft=fm.beginTransaction();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.parseColor("#B5EAEA"));
                }
                ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                ft.replace(R.id.categories_fr_container, container_content.newInstance("diabits_categorie",""));
                ft.commit();
                break;
            case "fitness_categorie":
                FragmentManager fmm= getSupportFragmentManager();
                getSupportActionBar().setTitle("Fitness Food");
                ColorDrawable colorDrawable2= new ColorDrawable(Color.parseColor("#C6EBC9"));
                main_footer.setBackground(colorDrawable2);
                main_activity.setBackgroundColor(Color.parseColor("#C6EBC9"));
                getSupportActionBar().setBackgroundDrawable(colorDrawable2);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.parseColor("#C6EBC9"));
                }
                FragmentTransaction fft=fmm.beginTransaction();
                fft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                fft.replace(R.id.categories_fr_container,container_content.newInstance("fitness_categorie",""));
                fft.commit();
                break;
            case "diet_categorie":
                FragmentManager fmmm= getSupportFragmentManager();
                FragmentTransaction ffft=fmmm.beginTransaction();
                getSupportActionBar().setTitle("Diet Food");
                ColorDrawable colorDrawable3= new ColorDrawable(Color.parseColor("#BEDCFA"));
                main_footer.setBackground(colorDrawable3);
                main_activity.setBackgroundColor(Color.parseColor("#BEDCFA"));
                getSupportActionBar().setBackgroundDrawable(colorDrawable3);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.parseColor("#BEDCFA"));
                }
                ffft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                ffft.replace(R.id.categories_fr_container,container_content.newInstance("diet_categorie",""));
                ffft.commit();
                break;
            case "hypertensive_categorie":
                FragmentManager fmmmm= getSupportFragmentManager();
                FragmentTransaction fffft=fmmmm.beginTransaction();
                getSupportActionBar().setTitle("Dypertensive Food");
                ColorDrawable colorDrawable4= new ColorDrawable(Color.parseColor("#FFBCBC"));
                main_footer.setBackground(colorDrawable4);
                main_activity.setBackgroundColor(Color.parseColor("#FFBCBC"));
                getSupportActionBar().setBackgroundDrawable(colorDrawable4);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.parseColor("#FFBCBC"));
                }
                fffft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                fffft.replace(R.id.categories_fr_container,container_content.newInstance("hypertensive_categorie",""));
                fffft.commit();
                break;
        }
    }

    @Override
    public void onFragmentClcik(meals newMeal) {
        getSupportActionBar().setTitle("Details");
        //active them
        ColorDrawable colorDrawable2= new ColorDrawable(getColor(R.color.details_second_color));
        main_footer.setBackground(colorDrawable2);
        ColorDrawable colorDrawable1= new ColorDrawable(getColor(R.color.details_first_color));
        main_activity.setBackgroundColor(getColor(R.color.details_first_color));
        getSupportActionBar().setBackgroundDrawable(colorDrawable1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getColor(R.color.details_first_color));
        }
        //bring fragment
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        //add animation to the fragment
        ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
        details d= details.newInstance(newMeal.getName(),newMeal.getDetails(),newMeal.getImg(),newMeal.getRate());
        ft.replace(R.id.categories_fr_container,d);
        ft.commit();
    }

    @Override
    public void numberOfFavourite(String num) {
        this.num=num;
    }

    @Override
    public void onContentClicked(meals newMeal) {
        //active them
        getSupportActionBar().setTitle("Details");
        main_txt.setVisibility(View.GONE);
        para_txt.setVisibility(View.GONE);
        ColorDrawable colorDrawable2= new ColorDrawable(getColor(R.color.details_second_color));
        main_footer.setBackground(colorDrawable2);
        ColorDrawable colorDrawable1= new ColorDrawable(getColor(R.color.details_first_color));
        main_activity.setBackgroundColor(getColor(R.color.details_first_color));
        getSupportActionBar().setBackgroundDrawable(colorDrawable1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getColor(R.color.details_first_color));
        }
        //bring fragment
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        //add animation to the fragment
        ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
        details d= details.newInstance(newMeal.getName(),newMeal.getDetails(),newMeal.getImg(),newMeal.getRate());
        ft.replace(R.id.categories_fr_container,d);
        ft.commit();

    }



}
