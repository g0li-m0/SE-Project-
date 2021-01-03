package com.example.finalproject.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.AppViewModel;
import com.example.finalproject.ApplicationItemAdapter;
import com.example.finalproject.R;
import com.example.finalproject.model.AppModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryOrAllActivity extends AppCompatActivity {

    String result;
    private ApplicationItemAdapter adapter;
    private DrawerLayout drawerLayout;
    AppViewModel appViewModel;

    // Filtering By
    final String bySize = "Most Size";
    final String byReview = "Most review";
    final String byRate = "Most rate";
    final String byInstalls = "Most installs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbarfilter);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initializeViewIds();

        initializeList();

        initializeFilteringText();

        String category = getIntent().getStringExtra("cat");
        handleQueryByCategoryAndState(category, appViewModel.filterState);

        appViewModel.getAppsByCategory().observe(this, new Observer<List<AppModel>>() {
            private static final String TAG = "Activity";

            @Override
            public void onChanged(List<AppModel> apps) {
                Log.i(TAG, "onChanged: Data received. Size: " + apps.size());
                Toast.makeText(CategoryOrAllActivity.this, "working", Toast.LENGTH_SHORT).show();
                adapter.updateApplications(apps);
            }
        });


    }

    private void handleQueryByCategoryAndState(String category, String filterState) {
        if (category == null && category.isEmpty()) {
            // All filtering
            switch (filterState) {
                case bySize:
                    appViewModel.fetchAllAppsBySize();
                    break;
                case byReview:
                    appViewModel.fetchAllAppsByReview();
                    break;
                case byRate:
                    appViewModel.fetchAllAppsByRate();
                    break;
                case byInstalls:
                    appViewModel.fetchAllAppsByInstalls();
                    break;
                default:
                    appViewModel.fetchAllApps();
                    break;
            }
        } else {
            // Category filtering
            switch (filterState) {
                case bySize:
                    appViewModel.fetchCategoryAppsBySize(category);
                    break;
                case byReview:
                    appViewModel.fetchCategoryAppsByReview(category);
                    break;
                case byRate:
                    appViewModel.fetchCategoryAppsByRate(category);
                    break;
                case byInstalls:
                    appViewModel.fetchCategoryAppsByInstalls(category);
                    break;
                default:
                    appViewModel.fetchCategoryApps(category);
                    break;
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void initializeFilteringText() {
        TextView filterText = findViewById(R.id.filter);
        filterText.setText("Filter by: " + appViewModel.filterState);
        filterText.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(CategoryOrAllActivity.this);
            builder.setTitle("Filter By :");
            String[] choose = {bySize, byReview, byRate, byInstalls};

            builder.setSingleChoiceItems(choose, -1, (dialog, which) -> result = choose[which])
                    .setPositiveButton("Apply", (dialog, which) -> {
                        appViewModel.filterState = result;
                        recreate(); // Recreates the activity by calling onCreate
                    }).create().show();

        });
    }

    private void initializeViewIds() {
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
        appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
    }

    private void initializeList() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        List<AppModel> apps = new ArrayList<>();
        adapter = new ApplicationItemAdapter(this, apps);
        recyclerView.setAdapter(adapter);
    }
}


