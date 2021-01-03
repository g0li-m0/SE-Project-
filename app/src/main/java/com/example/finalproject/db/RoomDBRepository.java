package com.example.finalproject.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.finalproject.model.AppModel;
import com.example.finalproject.model.ReviewModel;

import java.util.List;

public class RoomDBRepository {

    private final AppDatabase appDatabase;


    public RoomDBRepository(Context application) {
        appDatabase = AppDatabase.getInstance(application);
    }

    public void insert(List<AppModel> apps) {
        new Thread(() -> appDatabase.dao().insert(apps)).start();
    }

    public void insert_Review(List<ReviewModel> reviewModels) {
        new Thread(() -> appDatabase.reviewDao().insert(reviewModels)).start();
    }

    public void setAppAsFavorite(String appName, boolean isFavorite) {
        new Thread(() -> appDatabase.dao().setAppAsFavorite(appName, isFavorite)).start();
    }

    public LiveData<List<AppModel>> getAllFavoritesApps() {
        return appDatabase.dao().getFavoriteApps();
    }
    public LiveData<List<AppModel>> getAllApps() {
        return appDatabase.dao().getAllApps();
    }

    public LiveData<List<AppModel>> getAppByCategory(String category) {
        return appDatabase.dao().getByCategory(category);
    }

    public LiveData<List<AppModel>> getAppByContent_Rating(String content_rating) {
        return appDatabase.dao().getByContent_Rating(content_rating);
    }

    public LiveData<List<AppModel>> getAppByType(String type) {
        return appDatabase.dao().getByType(type);
    }

    public LiveData<List<AppModel>> getByMostInstalls() {
        return appDatabase.dao().getByMostInstalls();
    }

    public LiveData<List<AppModel>> getByMostRating() {
        return appDatabase.dao().getByMostRate();
    }

    public LiveData<List<AppModel>> getByMostSize() {
        return appDatabase.dao().getByMostSize();
    }

    public LiveData<List<ReviewModel>> getAllReviews() {
        return appDatabase.reviewDao().getAll();
    }

    public LiveData<List<ReviewModel>> getReviewByAppName(String name) {
        return appDatabase.reviewDao().getReviewByAppName(name);
    }

}



