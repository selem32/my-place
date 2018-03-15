package com.app.myplaces.presentation.main.home;

import android.support.v4.util.Pair;

import com.app.myplaces.presentation.base.BasePresenter;
import com.app.myplaces.presentation.base.BaseView;

public class HomeContract {

    interface View extends BaseView<Presenter> {
        void showLocationList(HomeAdapter adapter);

        void showDetail(Pair<android.view.View, String> p1, Pair<android.view.View, String> p2);
    }

    interface Presenter extends BasePresenter {
        void callPullListService();
    }
}
