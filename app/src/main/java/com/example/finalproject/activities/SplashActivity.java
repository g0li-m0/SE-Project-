package com.example.finalproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.db.RoomDBRepository;
import com.example.finalproject.model.AppModel;
import com.example.finalproject.model.ReviewModel;
import com.example.finalproject.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "Splash";
    private RoomDBRepository database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        database = new RoomDBRepository(this.getApplicationContext());

        database.getAllApps().observe(this, appModels -> {
            if (appModels.isEmpty()) {
                // if Database is Empty download again
                Toast.makeText(SplashActivity.this, "Downloading database...", Toast.LENGTH_SHORT).show();
                downloadData();
            } else {
                // Database already has data. Move without downloading
                moveToNextPage();
                Toast.makeText(SplashActivity.this, "Database already fetched", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * +
     * Downloads data and moves to next page if succeeded
     */
    private void downloadData() {
        Log.i(TAG, "onCreate: Downloading JSON file");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<AppModel>> call = apiService.getAllDetails();
        call.enqueue(new retrofit2.Callback<List<AppModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<AppModel>> call, @NonNull Response<List<AppModel>> response) {
                database.insert(response.body());

                Call<List<ReviewModel>> call1 = apiService.getAllReviews();
                call1.enqueue(new Callback<List<ReviewModel>>() {
                    @Override
                    public void onResponse(Call<List<ReviewModel>> call, Response<List<ReviewModel>> response) {
                        Log.i(TAG, "onResponse: Inserting data");
                        database.insert_Review(response.body());
                        Log.i(TAG, "onResponse: Moving to next page");
                        moveToNextPage();
                    }

                    @Override
                    public void onFailure(Call<List<ReviewModel>> call, Throwable t) {
                        Toast.makeText(SplashActivity.this, "Failed to fetch data from network", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<List<AppModel>> call, @NonNull Throwable t) {
                Toast.makeText(SplashActivity.this, "Failed to fetch data from network", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Move to next activity
     */
    private void moveToNextPage() {
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
        }, 3000);
    }
}
