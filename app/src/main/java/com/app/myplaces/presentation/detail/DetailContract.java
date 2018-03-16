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
    }

    interface Presenter extends BasePresenter {

    }
}
