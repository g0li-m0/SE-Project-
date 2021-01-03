package com.example.finalproject.db;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.finalproject.model.AppModel;
import com.example.finalproject.model.ReviewModel;
import com.example.finalproject.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Database(entities = {AppModel.class, ReviewModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "database";

    private static final String TAG = "Room";

    public abstract AppDao dao();

    public abstract ReviewDao reviewDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Log.i(TAG, "onCreate: Downloading JSON file");
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(ApiService.BaseUrl)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            ApiService apiService = retrofit.create(ApiService.class);

                            // All Apps
                            Call<List<AppModel>> call = apiService.getAllDetails();
                            call.enqueue(new retrofit2.Callback<List<AppModel>>() {

                                @Override
                                public void onResponse(Call<List<AppModel>> call, Response<List<AppModel>> response) {
                                    Log.i(TAG, "onResponse: Inserting data");
                                    RoomDBRepository db = new RoomDBRepository(context.getApplicationContext());
                                    db.insert(response.body());
                                }

                                @Override
                                public void onFailure(Call<List<AppModel>> call, Throwable t) {
                                    Log.e(TAG, "exception!!!!!", t);
                                }
                            });
                            // Review
                            Call<List<ReviewModel>> call1 = apiService.getAllReviews();
                            call1.enqueue(new retrofit2.Callback<List<ReviewModel>>() {
                                @Override
                                public void onResponse(Call<List<ReviewModel>> call, Response<List<ReviewModel>> response) {
                                    Log.i(TAG, "onResponse: Inserting data");
                                    RoomDBRepository db = new RoomDBRepository(context.getApplicationContext());
                                    db.insert_Review(response.body());
                                }

                                @Override
                                public void onFailure(Call<List<ReviewModel>> call, Throwable t) {
                                    Log.e(TAG, "exception!!!!!", t);
                                }
                            });


                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
