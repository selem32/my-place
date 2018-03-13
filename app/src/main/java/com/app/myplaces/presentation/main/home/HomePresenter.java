package com.app.myplaces.presentation.main.home;

import android.content.Context;
import android.util.Log;

import com.app.myplaces.business.LocationBusiness;
import com.app.myplaces.intrastructure.error.OperationError;
import com.app.myplaces.intrastructure.util.ServiceListener;
import com.app.myplaces.service.model.location.Location;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;
    private LocationBusiness mLocationBusiness;
    private RequestLocationsCallback mRequestLocationsCallback;

    public HomePresenter(Context context, HomeContract.View view) {
        this.mView = view;
        mLocationBusiness = new LocationBusiness(context);
        mRequestLocationsCallback = new RequestLocationsCallback();
        view.setPresenter(this);
    }

    @Override
    public void start() {
        callPullListService();
    }

    @Override
    public void callPullListService() {
        mLocationBusiness.callServiceLocationList(mRequestLocationsCallback);
    }

    private class RequestLocationsCallback extends ServiceListener<Location> {

        @Override
        public void onServiceSuccess(Location location) {
            //TODO
            Log.e("TESTE", "SUCESSO");
        }

        @Override
        public void onServiceError(OperationError error) {
            //TODO
            Log.e("TESTE", "FALHA");
        }
    }
}
