package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.model.AppModel;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<AppModel> favoritesList;
    private Context context;

    public FavoriteAdapter(List<AppModel> favoritesList, Context context) {
        this.favoritesList = favoritesList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView app_name, category, size, price, rate, type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            app_name = itemView.findViewById(R.id.app_name);
            category = itemView.findViewById(R.id.category);
            size = itemView.findViewById(R.id.size);
            price = itemView.findViewById(R.id.price);
            rate = itemView.findViewById(R.id.rate);
            type = itemView.findViewById(R.id.type);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_list, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppModel favoritesModel = favoritesList.get(position);
        holder.app_name.setText(favoritesModel.getApp_name());
        holder.category.setText("Category : " + favoritesModel.getCategory());
        holder.size.setText("Size : " + favoritesModel.getSize());
        holder.price.setText("Price : " + favoritesModel.getPrice());
        holder.rate.setText("Rating : " + favoritesModel.getRate());
        holder.type.setText("Type : " + favoritesModel.getTypes());
    }

    @Override
    public int getItemCount() {
        return favoritesList.size();
    }

    public void getAllApps(List<AppModel> favoritesModels) {
        this.favoritesList = favoritesModels;
        notifyDataSetChanged();
    }

}

