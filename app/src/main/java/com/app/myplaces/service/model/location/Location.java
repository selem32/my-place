package com.app.myplaces.service.model.location;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Location {

    @SerializedName("listLocations")
    private List<LocationItem> listLocations = null;

    public List<LocationItem> getListLocations() {
        return listLocations;
    }
}
