package com.app.myplaces.service.model.review;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Review {

    @SerializedName("reviews")
    private List<ReviewItem> reviews = null;

    public List<ReviewItem> getReviews() {
        return reviews;
    }
}
