package com.example.finalproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.db.AppDao;
import com.example.finalproject.db.AppDatabase;
import com.example.finalproject.db.RoomDBRepository;
import com.example.finalproject.model.AppModel;
import com.example.finalproject.model.ReviewModel;

import java.util.Collections;
import java.util.List;

public class AppViewModel extends AndroidViewModel {

    public LiveData<List<ReviewModel>> reviewmodel;
    public LiveData<List<AppModel>> favorites;
    public LiveData<List<ReviewModel>> reviewByAppName;
    public LiveData<List<AppModel>> allApps;
    public LiveData<List<AppModel>> categoryApps;
    public LiveData<List<AppModel>> contentratingApps;
    public LiveData<List<AppModel>> typeApps;
    public LiveData<List<AppModel>> searchedApps;
    public LiveData<List<AppModel>> MostReviews;
    public LiveData<List<AppModel>> MostInstalls;
    public LiveData<List<AppModel>> MostRating;
    public LiveData<List<AppModel>> MostSize;

    public String filterState = "";

    private final RoomDBRepository roomDBRepository;
    private final AppDao appDao;


    public AppViewModel(@NonNull Application application) {
        super(application);
        roomDBRepository = new RoomDBRepository(application);
        appDao = AppDatabase.getInstance(application.getApplicationContext()).dao();
    }

    public void fetchFavorites() {
        favorites = roomDBRepository.getAllFavoritesApps();
    }

    public void insert(List<AppModel> apps) {
        roomDBRepository.insert(apps);
    }

    /// All Apps

    public void fetchAllApps() {
        allApps = appDao.getAllApps();
    }
    public void fetchAllAppsBySize() {
        allApps = appDao.getAllOrderLargest();
    }
    public void fetchAllAppsByReview() {
        allApps = appDao.getAllOrderMostReviews();
    }
    public void fetchAllAppsByRate() {
        allApps = appDao.getAllOrderMostRate();
    }
    public void fetchAllAppsByInstalls() {
        allApps = appDao.getAllOrderMostInstalls();
    }

    /// Category

    public void fetchCategoryApps(String category) {
        categoryApps = appDao.getByCategory(category);
    }
    public void fetchCategoryAppsBySize(String category) {
        categoryApps = appDao.getByCategoryOrderLargest(category);
    }
    public void fetchCategoryAppsByReview(String category) {
        categoryApps = appDao.getByCategoryOrderMostReviews(category);
    }
    public void fetchCategoryAppsByRate(String category) {
        categoryApps = appDao.getByCategoryOrderMostRate(category);
    }
    public void fetchCategoryAppsByInstalls(String category) {
        categoryApps = appDao.getByCategoryOrderMostInstalls(category);
    }

    // Content rating

    public void fetchContent_RatingApps(String content_rating) {
        contentratingApps = appDao.getByContent_Rating(content_rating);
    }
    public void fetchContent_RatingAppsBySize(String content_rating) {
        contentratingApps = appDao.getByContent_RatingOrderLargest(content_rating);
    }
    public void fetchContent_RatingAppsByReview(String content_rating) {
        contentratingApps = appDao.getByContent_RatingOrderMostReviews(content_rating);
    }
    public void fetchContent_RatingAppsByRate(String content_rating) {
        contentratingApps = appDao.getByContent_RatingOrderMostRate(content_rating);
    }
    public void fetchContent_RatingAppsByInstalls(String content_rating) {
        contentratingApps = appDao.getByContent_RatingOrderMostInstalls(content_rating);
    }

    // Type

    public void fetchTypeApps(String type) {
        typeApps = appDao.getByType(type);
    }
    public void fetchTypeAppsBySize(String type) {
        typeApps = appDao.getByTypeOrderLargest(type);
    }
    public void fetchTypeAppsByReview(String type) {
        typeApps = appDao.getByTypeOrderMostReviews(type);
    }
    public void fetchTypeAppsByRate(String type) {
        typeApps = appDao.getByTypeOrderMostRate(type);
    }
    public void fetchTypeAppsByInstalls(String type) {
        typeApps = appDao.getByTypeOrderMostInstalls(type);
    }

    // Search

    public void searchApp(String search) {
        searchedApps = appDao.searchAll(search);
    }

    // Review

    public void fetchAllReviews() {
        reviewmodel = roomDBRepository.getAllReviews();
    }

    public void fetchReviewsApps(String review) {
        reviewByAppName = roomDBRepository.getReviewByAppName(review);
    }

    public void insertReview(ReviewModel model) {
        roomDBRepository.insert_Review(Collections.singletonList(model));
    }

    public void fetchMostInstalls() {
        MostInstalls = roomDBRepository.getByMostInstalls();
    }

    public void fetchMostRating() {
        MostRating = roomDBRepository.getByMostRating();
    }

    public void fetchMostSize() {
        MostSize = roomDBRepository.getByMostSize();
    }

    public LiveData<List<AppModel>> getSearchResult() {
        return searchedApps;
    }

    public LiveData<List<AppModel>> getMostSize() {
        return MostSize;
    }

    public LiveData<List<AppModel>> getMostRating() {
        return MostRating;
    }

    public LiveData<List<AppModel>> getMostInstalls() {
        return MostInstalls;
    }

    public LiveData<List<AppModel>> getMostreview() {
        return MostReviews;
    }

    public LiveData<List<AppModel>> getAllFavorites() {
        return favorites;
    }

    public LiveData<List<AppModel>> getAllApps() {
        return allApps;
    }

    public LiveData<List<AppModel>> getAppsByCategory() {
        return categoryApps;
    }

    public LiveData<List<AppModel>> getAppsByContent_Rating() {
        return contentratingApps;
    }

    public LiveData<List<AppModel>> getAppsByType() {
        return typeApps;
    }

    public LiveData<List<ReviewModel>> getAllAppsReview() {
        return reviewmodel;
    }

    public LiveData<List<ReviewModel>> getAppsByApps_name() {
        return reviewByAppName;
    }

}

