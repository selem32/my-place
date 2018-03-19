package com.app.myplaces.presentation.detail;


import android.graphics.Bitmap;

import com.app.myplaces.presentation.base.BasePresenter;
import com.app.myplaces.presentation.base.BaseView;

public class DetailContract {
    interface View extends BaseView<Presenter> {

        void configHeader(String title, double review, Bitmap image);

        void configPhotoList(DetailPhotoAdapter adapter);

        void configAbout(String source);

        void configReview(double review);

        void configTime(String time);

        void configAddress(String location);

        void configPhone(String phone);

        void configReviews(int qtde, DetailReviewAdapter adapter);

        void sharePlace(String about, String title, String location);
    }

    interface Presenter extends BasePresenter {
        void shareItem();

        void callService();
    }
}
