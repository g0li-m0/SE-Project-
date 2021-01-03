package com.example.finalproject.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "apps")
public class AppModel {

    public AppModel() {
    }

    public AppModel(@NonNull String app_name, String category, String rate, String reviews, String size, String installs, String types, String price, String content_rating, String genres, String last_update, String current_version, String android_version) {
        App_name = app_name;
        Category = category;
        Rate = rate;
        Reviews = reviews;
        Size = size;
        Installs = installs;
        Types = types;
        Price = price;
        Content_rating = content_rating;
        Genres = genres;
        Last_update = last_update;
        Current_version = current_version;
        Android_version = android_version;
        isFavorite = false;
    }

    @NonNull
    @PrimaryKey
    @SerializedName("App")
    public String App_name;

    @SerializedName("Category")
    public String Category;

    @SerializedName("Rating")
    public String Rate;

    @SerializedName("Reviews")
    public String Reviews;

    @SerializedName("Size")
    public String Size;

    @SerializedName("Installs")
    public String Installs;

    @SerializedName("Type")
    public String Types;

    @SerializedName("Price")
    public String Price;

    @SerializedName("Content Rating")
    public String Content_rating;

    @SerializedName("Genres")
    public String Genres;

    @SerializedName("Last Updated")
    public String Last_update;

    @SerializedName("Current Ver")
    public String Current_version;

    @SerializedName("Android Ver")
    public String Android_version;

    @SerializedName("isFav")
    @ColumnInfo(name = "is_favorite")
    public boolean isFavorite;


    @NonNull
    public String getApp_name() {
        return App_name;
    }

    public String getCategory() {
        return Category;
    }

    public String getRate() {
        return Rate;
    }

    public String getReviews() {
        return Reviews;
    }

    public String getSize() {
        return Size;
    }

    public String getInstalls() {
        return Installs;
    }

    public String getTypes() {
        return Types;
    }

    public String getPrice() {
        return Price;
    }

    public String getContent_rating() {
        return Content_rating;
    }

    public String getGenres() {
        return Genres;
    }

    public String getLast_update() {
        return Last_update;
    }

    public String getCurrent_version() {
        return Current_version;
    }

    public String getAndroid_version() {
        return Android_version;
    }

    public void setApp_name(@NonNull String app_name) {
        App_name = app_name;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public void setReviews(String reviews) {
        Reviews = reviews;
    }

    public void setSize(String size) {
        Size = size;
    }

    public void setInstalls(String installs) {
        Installs = installs;
    }

    public void setTypes(String types) {
        Types = types;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setContent_rating(String content_rating) {
        Content_rating = content_rating;
    }

    public void setGenres(String genres) {
        Genres = genres;
    }

    public void setLast_update(String last_update) {
        Last_update = last_update;
    }

    public void setCurrent_version(String current_version) {
        Current_version = current_version;
    }

    public void setAndroid_version(String android_version) {
        Android_version = android_version;
    }

    @Override
    public String toString() {
        return "Apps{" +
                "App_name='" + App_name + '\'' +
                ", Category='" + Category + '\'' +
                ", Rate='" + Rate + '\'' +
                ", Reviews='" + Reviews + '\'' +
                ", Size='" + Size + '\'' +
                ", Installs='" + Installs + '\'' +
                ", Types='" + Types + '\'' +
                ", Price='" + Price + '\'' +
                ", Content_rating='" + Content_rating + '\'' +
                ", Genres='" + Genres + '\'' +
                ", Last_update='" + Last_update + '\'' +
                ", Current_version='" + Current_version + '\'' +
                ", Android_version='" + Android_version + '\'' +
                '}';
    }
}
