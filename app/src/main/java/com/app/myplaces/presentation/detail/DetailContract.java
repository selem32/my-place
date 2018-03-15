package com.app.myplaces.presentation.detail;


import com.app.myplaces.presentation.base.BasePresenter;
import com.app.myplaces.presentation.base.BaseView;

public class DetailContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        void callPullListService();
    }
}
