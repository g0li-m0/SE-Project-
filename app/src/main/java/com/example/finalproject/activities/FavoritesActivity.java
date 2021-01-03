package com.example.finalproject.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.AppViewModel;
import com.example.finalproject.ApplicationItemAdapter;
import com.example.finalproject.FavoriteAdapter;
import com.example.finalproject.R;
import com.example.finalproject.db.RoomDBRepository;
import com.example.finalproject.model.AppModel;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ApplicationItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        TextView app_name = findViewById(R.id.app_name);
        TextView category = findViewById(R.id.category);
        TextView rate = findViewById(R.id.rate);
        TextView size = findViewById(R.id.size);
        TextView type = findViewById(R.id.type);
        TextView price = findViewById(R.id.price);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RoomDBRepository roomDBRepository = new RoomDBRepository(getApplication());
        List<AppModel> favoritesModels = new ArrayList<>();
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(favoritesModels, this);
        AppViewModel appViewModel = new ViewModelProvider(this).get(AppViewModel.class);

        // Add the app to your favorites list
        appViewModel.fetchFavorites();
        appViewModel.getAllFavorites().observe(this, favoritesModels1 -> {
            Toast.makeText(FavoritesActivity.this, "working", Toast.LENGTH_SHORT).show();
            favoriteAdapter.getAllApps(favoritesModels1);
            recyclerView.setAdapter(favoriteAdapter);
        });
    }
}