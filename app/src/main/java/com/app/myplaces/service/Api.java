package com.app.myplaces.service;

import com.app.myplaces.service.model.location.Location;
import com.app.myplaces.service.model.locationdetail.LocationDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("locations")
    Call<Location> getLocationList();

    @GET("/locations/{id}")
    Call<LocationDetail> getPullList(@Path("id") int id);

}
