package com.app.myplaces.presentation.base;

import com.app.myplaces.intrastructure.error.OperationError;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

    void showError(OperationError error);

    void showLoading(boolean isLoading);

}
