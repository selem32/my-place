package com.app.myplaces.presentation.detail;

import android.content.Context;

import com.app.myplaces.business.LocationDetailBusiness;
import com.app.myplaces.intrastructure.error.OperationError;
import com.app.myplaces.intrastructure.util.ImageExtraUtil;
import com.app.myplaces.intrastructure.util.ServiceListener;
import com.app.myplaces.service.model.location.LocationItem;
import com.app.myplaces.service.model.locationdetail.LocationDetail;

public class DetailPresenter implements DetailContract.Presenter {

    private Context mContext;
    private DetailContract.View mView;
    private LocationItem mLocationItem;
    private LocationDetailBusiness mLocationDetailBusiness;

    public DetailPresenter(Context mContext, DetailContract.View mView, LocationItem mLocationItem) {
        this.mContext = mContext;
        this.mView = mView;
        this.mLocationItem = mLocationItem;
        mView.setPresenter(this);

        mLocationDetailBusiness = new LocationDetailBusiness(mContext);
    }

    @Override
    public void start() {
        mView.configHeader(mLocationItem.getName(), mLocationItem.getReview(), ImageExtraUtil.getInstance().getImageLocation());
        mView.showLoading(true);
        mLocationDetailBusiness.callServiceLocationList(mLocationItem.getId(), new LocatioDetailnRequestCallback());
    }


    private class LocatioDetailnRequestCallback extends ServiceListener<LocationDetail> {

        @Override
        public void onServiceSuccess(LocationDetail locationDetail) {
            mView.showLoading(false);
            mView.configViewPager(locationDetail.getImageItemList());
            //TODO
        }

        @Override
        public void onServiceError(OperationError error) {
            mView.showLoading(false);
            mView.showError(error);
        }
    }
}
