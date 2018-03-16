package com.app.myplaces.presentation.main.home;

import android.widget.ImageView;

import com.app.myplaces.presentation.base.BasePresenter;
import com.app.myplaces.presentation.base.BaseView;
import com.app.myplaces.service.model.location.LocationItem;

public class HomeContract {

    interface View extends BaseView<Presenter> {
        void showLocationList(HomeAdapter adapter);

        void showDetail(LocationItem locationItem, ImageView view);
    }

    interface Presenter extends BasePresenter {
        void callPullListService();
    }
}
