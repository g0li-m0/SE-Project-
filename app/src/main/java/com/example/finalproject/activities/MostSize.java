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
import com.example.finalproject.R;
import com.example.finalproject.db.RoomDBRepository;
import com.example.finalproject.model.AppModel;

import java.util.ArrayList;
import java.util.List;

public class MostSize extends AppCompatActivity {

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
        TextView content_rating = findViewById(R.id.content_rating);
        TextView genres = findViewById(R.id.genres);
        TextView last_update = findViewById(R.id.last_update);
        TextView current_ver = findViewById(R.id.current_version);
        TextView android_ver = findViewById(R.id.android_version);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RoomDBRepository roomDBRepository = new RoomDBRepository(getApplication());
        List<AppModel> apps = new ArrayList<>();
        adapter = new ApplicationItemAdapter(this, apps);
        recyclerView.setAdapter(adapter);
        AppViewModel appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
        // fetch apps by MostSize
        appViewModel.fetchMostSize();
        appViewModel.getMostSize().observe(this, apps1 -> {
            Toast.makeText(MostSize.this, "working", Toast.LENGTH_SHORT).show();
            adapter.updateApplications(apps1);
        });

    }
}