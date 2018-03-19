package com.app.myplaces.service.model.review;

import com.google.gson.annotations.SerializedName;

public class ReviewItem {
    @SerializedName("name")
    private String name;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("review")
    private Double review;
    @SerializedName("opinion")
    private String opinion;
    @SerializedName("title")
    private String title;

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Double getReview() {
        return review;
    }

    public String getOpinion() {
        return opinion;
    }

    public String getTitle() {
        return title;
    }

    public String getUserInfo() {
        return String.format("%s, %s - %s", getName(), getCity(), getState());
    }
}
