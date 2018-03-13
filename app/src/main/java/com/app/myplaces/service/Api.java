package com.app.myplaces.service;

import com.app.myplaces.service.model.location.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("locations")
    Call<List<Location>> getLocationList();

//    @GET("/locations/{id}")
//    Call<LocationDetail> getPullList(@Path("id") int id);

}
