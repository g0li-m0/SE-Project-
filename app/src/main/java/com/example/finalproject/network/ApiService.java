package com.example.finalproject.network;

import com.example.finalproject.model.AppModel;
import com.example.finalproject.model.ReviewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    String BaseUrl = "http://androidkurd.ir/city/";

    @GET("resultdata.json")
    Call<List<AppModel>> getAllDetails();

    @GET("result2data.json")
    Call<List<ReviewModel>> getAllReviews();

}
