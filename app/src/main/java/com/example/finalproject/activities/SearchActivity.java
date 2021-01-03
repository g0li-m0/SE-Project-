package com.example.finalproject.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.AppViewModel;
import com.example.finalproject.ApplicationItemAdapter;
import com.example.finalproject.R;

import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class SearchActivity extends AppCompatActivity {

    private AppCompatEditText searchEdit;
    private AppCompatButton searchButton;
    private RecyclerView list;


    private ApplicationItemAdapter adapter;
    private AppViewModel appViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        appViewModel = new ViewModelProvider(this).get(AppViewModel.class);

        initializeViewIds();
        //  Search for app names
        searchButton.setOnClickListener(v -> {
            String text = searchEdit.getText().toString();
            if (text.isEmpty()) {
                Toast.makeText(this, "Enter a search to start", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Searching", Toast.LENGTH_SHORT).show();
            appViewModel.searchApp(text);
            appViewModel.getSearchResult().observe(this, appModels -> {
                if (appModels.isEmpty()) {
                    Toast.makeText(this, "No apps found", Toast.LENGTH_SHORT).show();
                    return;
                }
                adapter.updateApplications(appModels);
                list.scrollToPosition(0);
            });
        });
    }

    private void initializeViewIds() {
        searchEdit = findViewById(R.id.searchEdit);
        list = findViewById(R.id.list);
        searchButton = findViewById(R.id.searchButton);
        adapter = new ApplicationItemAdapter(this.getApplicationContext(), new ArrayList<>());
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setHasFixedSize(false);
        list.setAdapter(adapter);
    }
}