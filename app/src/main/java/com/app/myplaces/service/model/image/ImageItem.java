package com.app.myplaces.service.model.image;

import com.google.gson.annotations.SerializedName;

public class ImageItem {

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }
}
