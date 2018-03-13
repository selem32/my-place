package com.app.myplaces.intrastructure.util;


import com.app.myplaces.intrastructure.error.OperationError;

public abstract class ServiceListener<TResult> {

    public abstract void onServiceSuccess(final TResult result);

    public abstract void onServiceError(final OperationError error);
}
