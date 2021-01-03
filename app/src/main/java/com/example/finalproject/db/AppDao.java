package com.example.finalproject.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.finalproject.model.AppModel;

import java.util.List;

@Dao
public interface AppDao {

    @Query("SELECT * FROM apps")
    LiveData<List<AppModel>> getAllApps();

    @Query("SELECT * from apps order by CAST(Reviews AS int) DESC")
    LiveData<List<AppModel>> getAllOrderMostReviews();

    @Query("SELECT * from apps order by CAST(Size AS FLOAT) DESC")
    LiveData<List<AppModel>> getAllOrderLargest();

    @Query("SELECT * from apps order by CAST(Rate AS FLOAT) DESC")
    LiveData<List<AppModel>> getAllOrderMostRate();

    @Query("SELECT * from apps order by length(Installs) DESC")
    LiveData<List<AppModel>> getAllOrderMostInstalls();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<AppModel> apps);


    // Get by Category

    @Query("SELECT * from apps WHERE Category LIKE :category")
    LiveData<List<AppModel>> getByCategory(String category);

    @Query("SELECT * from apps WHERE Category LIKE :category order by CAST(Reviews AS int) DESC")
    LiveData<List<AppModel>> getByCategoryOrderMostReviews(String category);

    @Query("SELECT * from apps WHERE Category LIKE :category order by CAST(Size AS FLOAT) DESC")
    LiveData<List<AppModel>> getByCategoryOrderLargest(String category);

    @Query("SELECT * from apps WHERE Category LIKE :category order by CAST(rate AS FLOAT) DESC")
    LiveData<List<AppModel>> getByCategoryOrderMostRate(String category);

    @Query("SELECT * from apps WHERE Category LIKE :category order by length(Installs) DESC")
    LiveData<List<AppModel>> getByCategoryOrderMostInstalls(String category);


    // Get by content rating

    @Query("SELECT * from apps WHERE Content_rating LIKE :content_rating")
    LiveData<List<AppModel>> getByContent_Rating(String content_rating);

    @Query("SELECT * from apps WHERE Category LIKE :content_rating order by CAST(Reviews AS int) DESC")
    LiveData<List<AppModel>> getByContent_RatingOrderMostReviews(String content_rating);

    @Query("SELECT * from apps WHERE Category LIKE :content_rating order by CAST(Size AS FLOAT) DESC")
    LiveData<List<AppModel>> getByContent_RatingOrderLargest(String content_rating);

    @Query("SELECT * from apps WHERE Category LIKE :content_rating order by CAST(Rate AS FLOAT) DESC")
    LiveData<List<AppModel>> getByContent_RatingOrderMostRate(String content_rating);

    @Query("SELECT * from apps WHERE Category LIKE :content_rating order by length(Installs) DESC")
    LiveData<List<AppModel>> getByContent_RatingOrderMostInstalls(String content_rating);

    // Get by type

    @Query("SELECT * from apps WHERE Types = :type")
    LiveData<List<AppModel>> getByType(String type);

    @Query("SELECT * from apps WHERE Category = :type order by CAST(Reviews AS int) DESC")
    LiveData<List<AppModel>> getByTypeOrderMostReviews(String type);

    @Query("SELECT * from apps WHERE Category = :type order by CAST(Size AS FLOAT) DESC")
    LiveData<List<AppModel>> getByTypeOrderLargest(String type);

    @Query("SELECT * from apps WHERE Category = :type order by CAST(Rate AS FLOAT) DESC")
    LiveData<List<AppModel>> getByTypeOrderMostRate(String type);

    @Query("SELECT * from apps WHERE Category = :type order by length(Installs) DESC")
    LiveData<List<AppModel>> getByTypeOrderMostInstalls(String type);


    // Favorite

    @Query("select * from apps where is_favorite = 1")
    LiveData<List<AppModel>> getFavoriteApps();

    @Query("update apps set is_favorite = :isFavorite where App_name = :appName")
    void setAppAsFavorite(String appName, boolean isFavorite);

    // Search query

    @Query("SELECT * from apps WHERE App_name LIKE '%' || :search || '%'")
    LiveData<List<AppModel>> searchAll(String search);

    @Query("SELECT * from apps WHERE App_name LIKE :search order by CAST(Reviews AS int) DESC")
    LiveData<List<AppModel>> searchOrderMostReviews(String search);

    @Query("SELECT * from apps WHERE App_name LIKE :search order by CAST(Size AS FLOAT) DESC")
    LiveData<List<AppModel>> searchOrderLargest(String search);

    @Query("SELECT * from apps WHERE App_name LIKE :search order by CAST(Rate AS FLOAT) DESC")
    LiveData<List<AppModel>> searchOrderMostRate(String search);

    @Query("SELECT * from apps WHERE App_name LIKE :search order by length(Installs) DESC")
    LiveData<List<AppModel>> searchOrderMostInstalls(String search);

    // Some crap

    @Query("SELECT * FROM apps ORDER BY Reviews DESC")
    LiveData<List<AppModel>> getAllByMostReviews();

    @Query("SELECT * from apps ORDER BY Installs DESC")
    LiveData<List<AppModel>> getByMostInstalls();

    @Query("SELECT * from apps ORDER BY Rate DESC")
    LiveData<List<AppModel>> getByMostRate();

    @Query("SELECT * from apps ORDER BY Size DESC")
    LiveData<List<AppModel>> getByMostSize();

}
