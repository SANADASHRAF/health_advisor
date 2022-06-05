package com.example.health_advisor.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.health_advisor.R;
import com.example.health_advisor.databases.my_database;
import com.example.health_advisor.objects.meals;
import com.example.health_advisor.interfaces.onRecycleViewItemClickListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class categorie_content_adapter extends RecyclerView.Adapter<categorie_content_adapter.cat_content> {
    ArrayList<meals> contents_lists;
    onRecycleViewItemClickListener listener;
    my_database db;

    public categorie_content_adapter(ArrayList<meals> contents_lists, Context context, onRecycleViewItemClickListener listener) {
        this.contents_lists = contents_lists;
        this.listener=listener;
        db=new my_database(context);
    }

    @NonNull
    @Override
    public cat_content onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.categorie_content_layout,null,false);
       cat_content newContent=new cat_content(v);
        return newContent;
    }

    @Override
    public void onBindViewHolder(@NonNull cat_content holder, int position) {
        meals new_cat_con=contents_lists.get(position);
        holder.con_cat_name.setText(new_cat_con.getCategorie_name());
        holder.con_cat_rate.setRating(new_cat_con.getRate());
        holder.con_detial.setText(new_cat_con.getDetails());
        holder.con_img.setImageResource(new_cat_con.getImg());
        holder.con_name.setText(new_cat_con.getName());
        holder.con_ID=new_cat_con.getId();
        holder.cat_content_card.setCardBackgroundColor(new_cat_con.getColor());
    }

    @Override
    public int getItemCount() {
        return contents_lists.size();
    }

    class cat_content extends RecyclerView.ViewHolder {
        ImageView con_img;
        TextView con_name;
        TextView con_detial;
        TextView con_cat_name;
        RatingBar con_cat_rate;
        CardView cat_content_card;
        LottieAnimationView lot_fav;
        Boolean startAnime=false;
        int con_ID;
        public cat_content(@NonNull View itemView) {
            super(itemView);
            con_img=itemView.findViewById(R.id.image_cat_content);
            con_cat_name=itemView.findViewById(R.id.categoriy_name_cat_content);
            con_detial=itemView.findViewById(R.id.details_cat_content);
            con_name=itemView.findViewById(R.id.name_cat_content);
            lot_fav=itemView.findViewById(R.id.lot_fav_animation_cat_content);
            con_cat_rate=itemView.findViewById(R.id.ratebar_cat_con);
            cat_content_card=itemView.findViewById(R.id.cat_content_cardView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClickListener(con_ID);
                }
            });
            lot_fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  if (!db.getMealsWithid(con_ID).getFavourite().equals("yes")){
                      lot_fav.playAnimation();
                      meals m=db.getMealsWithid(con_ID);
                      m.setFavourite("yes");
                      boolean b=db.setFavMealWithID(m);
                      if (b==true)
                          Toast.makeText(view.getContext(), "done", Toast.LENGTH_SHORT).show();
                  }
                  else {
                      Snackbar.make(view,"It's already added to favourite !!",Snackbar.LENGTH_SHORT).show();
                  }
                }
            });

        }
    }
}
