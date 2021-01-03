package com.example.finalproject.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

public class AccountInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account__info);
        // save sign up info for display on Navigation Drawer Header
        SharedPreferences sharedPreferences = getSharedPreferences("PREFRENCE", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String gmail = sharedPreferences.getString("gmail", "");
        TextView name = findViewById(R.id.username);
        TextView Gmail = findViewById(R.id.gmail);
        name.setText(username);
        Gmail.setText(gmail);
    }
}