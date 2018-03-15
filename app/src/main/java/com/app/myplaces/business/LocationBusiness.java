package com.app.myplaces.business;

import android.content.Context;

import com.app.myplaces.intrastructure.error.OperationError;
import com.app.myplaces.intrastructure.util.MockUtil;
import com.app.myplaces.intrastructure.util.NetworkUtil;
import com.app.myplaces.intrastructure.util.ServiceListener;
import com.app.myplaces.service.Api;
import com.app.myplaces.service.ApiInstance;
import com.app.myplaces.service.model.image.ImageList;
import com.app.myplaces.service.model.location.Location;
import com.app.myplaces.service.model.location.LocationItem;
import com.app.myplaces.service.repository.LocationRepository;

public class LocationBusiness {
    private Context mContext;
    private LocationRepository mLocationRepository;
    private ServiceListener<Location> mCallback;

    public LocationBusiness(Context context) {
        mContext = context;
        mLocationRepository = new LocationRepository(ApiInstance.getInstance().getAPI().create(Api.class),
                new NetworkUtil(context));
    }

    public void callServiceLocationList(ServiceListener<Location> callback) {
        mCallback = callback;
        mLocationRepository.requestLocationList(new LocationRequestCallback());
    }

    private class LocationRequestCallback extends ServiceListener<Location> {

        @Override
        public void onServiceSuccess(Location location) {
            processRestult(location);
            mCallback.onServiceSuccess(location);
        }

        @Override
        public void onServiceError(OperationError error) {
            mCallback.onServiceError(error);
        }

        private void processRestult(Location location) {
            MockUtil mockUtil = new MockUtil();
            ImageList imageList = mockUtil.getImageList(mContext);

            int counter = 0;
            for (LocationItem locationItem : location.getListLocations()) {

                if (counter >= imageList.getImages().size()) {
                    counter = 0;
                }
                locationItem.setUrlImage(imageList.getImages().get(counter).getUrl());
                counter++;
            }
        }

    }
}
