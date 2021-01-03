package com.example.finalproject.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

public class MainActivity extends AppCompatActivity {

    EditText input_username, input_password, input_email;
    Button signup;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = findViewById(R.id.signup);
        textView = findViewById(R.id.textViewLink);

        //Go from registration page to the Login page
        textView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        //sign up page
        signup.setOnClickListener(v -> {
            input_username = findViewById(R.id.username);
            input_password = findViewById(R.id.password);
            input_email = findViewById(R.id.email);
            SharedPreferences sharedPreferences = getSharedPreferences("PREFRENCE", MODE_PRIVATE);
            String name = input_username.getText().toString().trim();
            String gmail = input_email.getText().toString().trim();
            String password = input_password.getText().toString().trim();

            //  Enter information
            if (!name.isEmpty() && !password.isEmpty() && !gmail.isEmpty()) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", name);
                editor.putString("gmail", gmail);
                editor.putInt("", Integer.parseInt(password));
                editor.apply();
                //Go from sign up page to the Main page
                Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                startActivity(intent);

            }
        });
    }
}