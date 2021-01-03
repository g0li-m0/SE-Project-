package com.example.finalproject.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.finalproject.model.ReviewModel;

import java.util.List;

@Dao
public interface ReviewDao {

    @Query("SELECT * FROM review")
    LiveData<List<ReviewModel>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ReviewModel> apps);

    @Query("SELECT * from review WHERE App_name LIKE :appName")
    LiveData<List<ReviewModel>> getReviewByAppName(String appName);

}

