package com.app.myplaces.service.model.location;

import com.google.gson.annotations.SerializedName;

public class LocationItem {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("review")
    private double review;
    @SerializedName("type")
    private String type;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getReview() {
        return review;
    }

    public String getType() {
        return type;
    }
}
