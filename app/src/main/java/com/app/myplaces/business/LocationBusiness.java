package com.app.myplaces.business;

import android.content.Context;

import com.app.myplaces.intrastructure.util.NetworkUtil;
import com.app.myplaces.intrastructure.util.ServiceListener;
import com.app.myplaces.service.Api;
import com.app.myplaces.service.ApiInstance;
import com.app.myplaces.service.model.location.Location;
import com.app.myplaces.service.repository.LocationRepository;

public class LocationBusiness {
    private LocationRepository mLocationRepository;

    public LocationBusiness(Context context) {
        mLocationRepository = new LocationRepository(ApiInstance.getInstance().getAPI().create(Api.class),
                new NetworkUtil(context));
    }

    public void callServiceLocationList(ServiceListener<Location> callback) {
        mLocationRepository.requestLocationList(callback);
    }
}
