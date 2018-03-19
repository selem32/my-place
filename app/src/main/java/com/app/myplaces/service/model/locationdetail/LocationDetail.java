package com.app.myplaces.service.model.locationdetail;

import com.app.myplaces.service.model.image.ImageItem;
import com.app.myplaces.service.model.review.ReviewItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationDetail {

    @SerializedName("name")
    private String name;
    @SerializedName("review")
    private Double review;
    @SerializedName("type")
    private String type;
    @SerializedName("id")
    private Integer id;
    @SerializedName("about")
    private String about;
    @SerializedName("schedule")
    private Schedule schedule;
    @SerializedName("phone")
    private String phone;
    @SerializedName("adress")
    private String adress;
    private List<ImageItem> imageItemList;
    private List<ReviewItem> reviewItemList;

    public String getName() {
        return name;
    }

    public Double getReview() {
        return review;
    }

    public String getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public String getAbout() {
        return about;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdress() {
        return adress;
    }

    public List<ImageItem> getImageItemList() {
        return imageItemList;
    }

    public void setImageItemList(List<ImageItem> imageItemList) {
        this.imageItemList = imageItemList;
    }

    public List<ReviewItem> getReviewItemList() {
        return reviewItemList;
    }

    public void setReviewItemList(List<ReviewItem> reviewItemList) {
        this.reviewItemList = reviewItemList;
    }
}
