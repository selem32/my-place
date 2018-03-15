package com.app.myplaces.service.model.image;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageList {

    @SerializedName("images")
    private List<ImageItem> images = null;

    public List<ImageItem> getImages() {
        return images;
    }
}
