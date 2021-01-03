package com.example.finalproject.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.AppViewModel;
import com.example.finalproject.ApplicationItemAdapter;
import com.example.finalproject.R;
import com.example.finalproject.db.RoomDBRepository;
import com.example.finalproject.model.AppModel;

import java.util.ArrayList;
import java.util.List;

public class ContentRatingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ApplicationItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        TextView app_name = findViewById(R.id.app_name);
        TextView category = findViewById(R.id.category);
        TextView rate = findViewById(R.id.rate);
        TextView reviews = findViewById(R.id.reviews);
        TextView size = findViewById(R.id.size);
        TextView installs = findViewById(R.id.installs);
        TextView type = findViewById(R.id.type);
        TextView price = findViewById(R.id.price);
        TextView content_rating1 = findViewById(R.id.content_rating);
        TextView genres = findViewById(R.id.genres);
        TextView last_update = findViewById(R.id.last_update);
        TextView current_ver = findViewById(R.id.current_version);
        TextView android_ver = findViewById(R.id.android_version);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RoomDBRepository roomDBRepository = new RoomDBRepository(getApplication());
        List<AppModel> apps = new ArrayList<>();
        adapter = new ApplicationItemAdapter(this, apps);
        AppViewModel appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
        String content_rating = getIntent().getStringExtra("content_rating");
        if (content_rating == null && content_rating.isEmpty()) {
            // fetch all of apps
            appViewModel.fetchAllApps();
        } else {
            // fetch apps by content_rating
            appViewModel.fetchContent_RatingApps(content_rating);
        }
        appViewModel.getAppsByContent_Rating().observe(this, apps1 -> {
            Toast.makeText(ContentRatingActivity.this, "working", Toast.LENGTH_SHORT).show();
            Log.e("content_rating", "why does not display?");
            adapter.updateApplications(apps1);
            recyclerView.setAdapter(adapter);
        });
    }
}