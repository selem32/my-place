package com.app.myplaces.service.model.locationdetail;

import com.google.gson.annotations.SerializedName;

public class Days {

    @SerializedName("open")
    private String open;
    @SerializedName("close")
    private String close;

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }
}
