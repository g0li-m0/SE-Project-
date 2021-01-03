package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.activities.ReviewActivity;
import com.example.finalproject.db.RoomDBRepository;
import com.example.finalproject.model.AppModel;

import java.util.List;

public class ApplicationItemAdapter extends RecyclerView.Adapter<ApplicationItemAdapter.AppViewHolder> {

    private List<AppModel> appModelList;
    private final Context context;
    RoomDBRepository roomDBRepository;

    public ApplicationItemAdapter(Context context, List<AppModel> appModelList) {
        this.appModelList = appModelList;
        this.context = context;
        roomDBRepository = new RoomDBRepository(context);
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder {

        TextView app_name, category, reviews, installs, rate, price, size, type, content_rating, genres, last_upadte, current_ver, android_ver;
        Button button;
        ImageView fav;

        public AppViewHolder(View view) {
            super(view);
            app_name = view.findViewById(R.id.app_name);
            category = view.findViewById(R.id.category);
            rate = view.findViewById(R.id.rate);
            reviews = view.findViewById(R.id.reviews);
            size = view.findViewById(R.id.size);
            installs = view.findViewById(R.id.installs);
            type = view.findViewById(R.id.type);
            price = view.findViewById(R.id.price);
            content_rating = view.findViewById(R.id.content_rating);
            genres = view.findViewById(R.id.genres);
            last_upadte = view.findViewById(R.id.last_update);
            current_ver = view.findViewById(R.id.current_version);
            android_ver = view.findViewById(R.id.android_version);
            button = view.findViewById(R.id.show_comments);
            fav = view.findViewById(R.id.like);


        }
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new AppViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        AppModel appModel = appModelList.get(position);
        holder.app_name.setText(appModel.getApp_name());
        holder.category.setText("Category : " + appModel.getCategory());
        holder.rate.setText("Rating : " + appModel.getRate());
        holder.reviews.setText("Reviews : " + appModel.getReviews());
        holder.size.setText("Size : " + appModel.getSize());
        holder.installs.setText("Installs : " + appModel.getInstalls());
        holder.type.setText("Type : " + appModel.getTypes());
        holder.price.setText("Price : " + appModel.getPrice());
        holder.content_rating.setText("Content Rating : " + appModel.getContent_rating());
        holder.genres.setText("Genres : " + appModel.getGenres());
        holder.last_upadte.setText("Last Update : " + appModel.getLast_update());
        holder.current_ver.setText("Current Version : " + appModel.getCurrent_version());
        holder.android_ver.setText("Android Version : " + appModel.getAndroid_version());
        if (appModel.isFavorite) {
            holder.fav.setImageResource(R.drawable.ic_baseline_redfavorite_24);
        } else {
            holder.fav.setImageResource(R.drawable.ic_baseline_favorite_24);
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReviewActivity.class);
                intent.putExtra("comment", appModel.getApp_name());
                context.startActivity(intent);
            }
        });
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set app as a favorite by updating its favorite field
                boolean setToFavorite = !appModel.isFavorite; // not(isFavorite), since it's being clicked
                roomDBRepository.setAppAsFavorite(appModel.App_name, setToFavorite);
            }
        });
    }


    @Override
    public int getItemCount() {
        return appModelList.size();
    }

    /**
     * Called when dataset is updated
     */
    public void updateApplications(List<AppModel> appModelList) {
        this.appModelList = appModelList;
        notifyDataSetChanged();
    }
}
