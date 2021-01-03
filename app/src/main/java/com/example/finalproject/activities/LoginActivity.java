package com.example.finalproject.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

public class LoginActivity extends AppCompatActivity {

    EditText input_username, input_password;
    TextView textView1, textView2;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView1 = findViewById(R.id.account1);
        textView2 = findViewById(R.id.textViewLink);
        login = findViewById(R.id.login);

        //Go from login page to the registration page
        textView2.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        });

        // Login page
        login.setOnClickListener(v -> {
            input_username = findViewById(R.id.username);
            input_password = findViewById(R.id.password);
            SharedPreferences sharedPreferences = getSharedPreferences("PREFRENCE", MODE_PRIVATE);
            String name = input_username.getText().toString().trim();
            String password = input_password.getText().toString().trim();

            // Enter information
            if (!name.isEmpty() && !password.isEmpty()) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", name);
                editor.putInt("", Integer.parseInt(password));
                editor.apply();
                // Go from Login page to the Main page
                Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                startActivity(intent);

            }
        });
    }
}