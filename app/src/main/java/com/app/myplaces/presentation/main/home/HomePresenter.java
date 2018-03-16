package com.app.myplaces.presentation.main.home;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.app.myplaces.business.LocationBusiness;
import com.app.myplaces.intrastructure.error.OperationError;
import com.app.myplaces.intrastructure.util.ServiceListener;
import com.app.myplaces.service.model.location.Location;
import com.app.myplaces.service.model.location.LocationItem;

public class HomePresenter implements HomeContract.Presenter {

    private Context mContext;
    private HomeContract.View mView;
    private LocationBusiness mLocationBusiness;
    private RequestLocationsCallback mRequestLocationsCallback;

    public HomePresenter(Context context, HomeContract.View view) {
        this.mView = view;
        mContext = context;
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
            mView.showLocationList(new HomeAdapter(mContext, location, new OnItemSelectedCallback()));
        }

        @Override
        public void onServiceError(OperationError error) {
            //TODO
            Log.e("TESTE", "FALHA");
        }
    }

    private class OnItemSelectedCallback implements OnItemSelected {

        @Override
        public void onItemClick(LocationItem locationItem, ImageView view) {
            mView.showDetail(locationItem, view);
        }
    }

    public interface OnItemSelected {
        void onItemClick(LocationItem locationItem, ImageView view);
    }
}
