package com.app.myplaces.presentation.detail;


import android.graphics.Bitmap;

import com.app.myplaces.presentation.base.BasePresenter;
import com.app.myplaces.presentation.base.BaseView;
import com.app.myplaces.service.model.image.ImageItem;

import java.util.List;

public class DetailContract {
    interface View extends BaseView<Presenter> {
        void configHeader(String title, double review, Bitmap image);

        void configViewPager(List<ImageItem> imageItemList);
    }

    interface Presenter extends BasePresenter {

    }
}
