package com.app.myplaces.service.model.location;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("review")
    private Integer review;
    @SerializedName("type")
    private String type;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getReview() {
        return review;
    }

    public String getType() {
        return type;
    }
}
