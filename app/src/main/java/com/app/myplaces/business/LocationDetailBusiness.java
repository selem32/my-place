package com.app.myplaces.business;

import android.content.Context;

import com.app.myplaces.intrastructure.error.OperationError;
import com.app.myplaces.intrastructure.util.MockUtil;
import com.app.myplaces.intrastructure.util.NetworkUtil;
import com.app.myplaces.intrastructure.util.ServiceListener;
import com.app.myplaces.service.Api;
import com.app.myplaces.service.ApiInstance;
import com.app.myplaces.service.model.image.ImageList;
import com.app.myplaces.service.model.locationdetail.LocationDetail;
import com.app.myplaces.service.repository.LocationRepository;

public class LocationDetailBusiness {
    private Context mContext;
    private LocationRepository mLocationRepository;
    private ServiceListener<LocationDetail> mCallback;

    public LocationDetailBusiness(Context context) {
        mContext = context;
        mLocationRepository = new LocationRepository(ApiInstance.getInstance().getAPI().create(Api.class),
                new NetworkUtil(context));
    }

    public void callServiceLocationList(int locationId, ServiceListener<LocationDetail> callback) {
        mCallback = callback;
        mLocationRepository.requestLocationDetail(locationId, new LocatioDetailnRequestCallback());
    }

    private class LocatioDetailnRequestCallback extends ServiceListener<LocationDetail> {

        @Override
        public void onServiceSuccess(LocationDetail locationDetail) {
            processRestult(locationDetail);
            mCallback.onServiceSuccess(locationDetail);
        }

        @Override
        public void onServiceError(OperationError error) {
            mCallback.onServiceError(error);
        }

        private void processRestult(LocationDetail locationDetail) {
            MockUtil mockUtil = new MockUtil();
            ImageList imageList = mockUtil.getImageList(mContext);
            locationDetail.setImageItemList(imageList.getImages());
        }

    }
}
