package com.example.health_advisor.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health_advisor.R;
import com.example.health_advisor.interfaces.onRecycleViewItemClickListener;
import com.example.health_advisor.objects.meals;

import java.util.ArrayList;

public class meals_adapter_favourite extends RecyclerView.Adapter<meals_adapter_favourite.mealsView> {
    ArrayList<meals> meals_list;
    onRecycleViewItemClickListener listener;

    public meals_adapter_favourite(ArrayList<meals> meals_list, onRecycleViewItemClickListener listener) {
        this.meals_list = meals_list;
        this.listener=listener;
    }

    @NonNull
    @Override
    public mealsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_favouite_meals_layout,null,false);
        mealsView newMeals=new mealsView(v);
        return newMeals;
    }

    @Override
    public void onBindViewHolder(@NonNull mealsView holder, int position) {
            meals newMeal=meals_list.get(position);
            holder.m_name.setText(newMeal.getName());
            holder.m_categorie.setText(newMeal.getCategorie_name());
            holder.m_img.setImageResource(newMeal.getImg());
            holder.m_rate.setRating(newMeal.getRate());
            holder.m_details.setText(newMeal.getDetails());
            holder.id=newMeal.getId();
    }

    @Override
    public int getItemCount() {
        return meals_list.size();
    }

    class mealsView extends RecyclerView.ViewHolder {
        TextView m_name;
        TextView m_details;
        ImageView m_img;
        RatingBar m_rate;
        TextView m_categorie;
        int id;
        public mealsView(@NonNull View itemView) {
            super(itemView);
            m_name=itemView.findViewById(R.id.meal_name);
            m_details=itemView.findViewById(R.id.meal_detials);
            m_img=itemView.findViewById(R.id.meal_img);
            m_rate=itemView.findViewById(R.id.meal_rate);
            m_categorie=itemView.findViewById(R.id.meal_categoriy_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClickListener(id);
                }
            });
        }
    }
}
