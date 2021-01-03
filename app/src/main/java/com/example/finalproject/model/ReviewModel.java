package com.example.finalproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
@Entity(tableName = "review")

public class ReviewModel {

    public ReviewModel() {
    }

    public ReviewModel(@NonNull String app_name, String user_Review, String sentimate, String polarity, String subjectivity) {
        App_name = app_name;
        User_Review = user_Review;
        this.sentimate = sentimate;
        Polarity = polarity;
        Subjectivity = subjectivity;
    }


    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @SerializedName("App")
    private String App_name;

    @SerializedName("Translated_Review")
    private String User_Review;

    @SerializedName("Sentiment")
    private String sentimate;

    @SerializedName("Sentiment_Polarity")
    private String Polarity;

    @SerializedName("Sentiment_Subjectivity")
    private String Subjectivity;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getApp_name() {
        return App_name;
    }

    public String getUser_Review() {
        return User_Review;
    }

    public String getSentimate() {
        return sentimate;
    }

    public String getPolarity() {
        return Polarity;
    }

    public String getSubjectivity() {
        return Subjectivity;
    }

    public void setApp_name(@NonNull String app_name) {
        App_name = app_name;
    }

    public void setUser_Review(String user_Review) {
        User_Review = user_Review;
    }

    public void setSentimate(String sentimate) {
        this.sentimate = sentimate;
    }

    public void setPolarity(String polarity) {
        Polarity = polarity;
    }

    public void setSubjectivity(String subjectivity) {
        Subjectivity = subjectivity;
    }

    @Override
    public String toString() {
        return "ReviewModel{" +
                "App_name='" + App_name + '\'' +
                ", User_Review='" + User_Review + '\'' +
                ", sentimate='" + sentimate + '\'' +
                ", Polarity='" + Polarity + '\'' +
                ", Subjectivity='" + Subjectivity + '\'' +
                '}';
    }

}
