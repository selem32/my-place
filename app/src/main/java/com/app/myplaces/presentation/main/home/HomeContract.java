package com.app.myplaces.presentation.main.home;

import com.app.myplaces.presentation.base.BasePresenter;
import com.app.myplaces.presentation.base.BaseView;

public class HomeContract {

    interface View extends BaseView<Presenter> {
        void showLocationList(HomeAdapter adapter);
    }

    interface Presenter extends BasePresenter {
        void callPullListService();
    }
}
