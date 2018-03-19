package com.app.myplaces.presentation.detail;

import android.content.Context;

import com.app.myplaces.R;
import com.app.myplaces.business.LocationDetailBusiness;
import com.app.myplaces.intrastructure.error.OperationError;
import com.app.myplaces.intrastructure.util.ImageExtraUtil;
import com.app.myplaces.intrastructure.util.ServiceListener;
import com.app.myplaces.service.model.image.ImageItem;
import com.app.myplaces.service.model.location.LocationItem;
import com.app.myplaces.service.model.locationdetail.LocationDetail;

public class DetailPresenter implements DetailContract.Presenter {

    private Context mContext;
    private DetailContract.View mView;
    private LocationItem mLocationItem;
    private LocationDetailBusiness mLocationDetailBusiness;
    private LocationDetail mLocationDetail;

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
        mView.configReview(mLocationItem.getReview());
        callService();
    }

    @Override
    public void callService() {
        mView.showLoading(true);
        mLocationDetailBusiness.callServiceLocationList(mLocationItem.getId(), new LocatioDetailnRequestCallback());
    }

    @Override
    public void shareItem() {
        mView.sharePlace(mLocationDetail.getAbout(), mLocationDetail.getName(), mLocationDetail.getAdress());
    }

    private class LocatioDetailnRequestCallback extends ServiceListener<LocationDetail> {

        @Override
        public void onServiceSuccess(LocationDetail locationDetail) {
            mLocationDetail = locationDetail;
            mView.showLoading(false);
            mView.configPhotoList(new DetailPhotoAdapter(mContext, locationDetail.getImageItemList(),
                    new OnPhotoCallback()));
            mView.configAbout(locationDetail.getAbout());
            mView.configPhone(locationDetail.getPhone());
            mView.configAddress(locationDetail.getAdress());
            mView.configReviews(locationDetail.getReviewItemList().size(), new DetailReviewAdapter(mContext, locationDetail.getReviewItemList()));
            mView.configTime(generateTime(locationDetail));
        }

        @Override
        public void onServiceError(OperationError error) {
            mView.showLoading(false);
            mView.showError(error);
        }

        private String generateTime(LocationDetail locationDetail) {
            String time = "";

            if (locationDetail.getSchedule().getMonday() != null) {
                time += String.format(mContext.getString(R.string.detail_time_monday), locationDetail.getSchedule().getTimeOpen(locationDetail.getSchedule().getMonday()));
            }

            if (locationDetail.getSchedule().getTuesday() != null) {
                time += String.format(mContext.getString(R.string.detail_time_tuesday), locationDetail.getSchedule().getTimeOpen(locationDetail.getSchedule().getTuesday()));
            }

            if (locationDetail.getSchedule().getWednesday() != null) {
                time += String.format(mContext.getString(R.string.detail_time_wednesday), locationDetail.getSchedule().getTimeOpen(locationDetail.getSchedule().getWednesday()));
            }

            if (locationDetail.getSchedule().getThursday() != null) {
                time += String.format(mContext.getString(R.string.detail_time_thursday), locationDetail.getSchedule().getTimeOpen(locationDetail.getSchedule().getThursday()));
            }

            if (locationDetail.getSchedule().getFriday() != null) {
                time += String.format(mContext.getString(R.string.detail_time_friday), locationDetail.getSchedule().getTimeOpen(locationDetail.getSchedule().getFriday()));
            }

            if (locationDetail.getSchedule().getSaturday() != null) {
                time += String.format(mContext.getString(R.string.detail_time_saturday), locationDetail.getSchedule().getTimeOpen(locationDetail.getSchedule().getSaturday()));
            }

            if (locationDetail.getSchedule().getSunday() != null) {
                time += String.format(mContext.getString(R.string.detail_time_sunday), locationDetail.getSchedule().getTimeOpen(locationDetail.getSchedule().getSunday()));
            }

            return time;
        }
    }

    private class OnPhotoCallback implements OnPhotoClickListener {

        @Override
        public void onItemSelected(ImageItem imageItem) {
            //TODO
        }
    }

    public interface OnPhotoClickListener {
        void onItemSelected(ImageItem imageItem);
    }
}
