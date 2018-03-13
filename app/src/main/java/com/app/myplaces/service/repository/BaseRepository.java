package com.app.myplaces.service.repository;


import com.app.myplaces.intrastructure.Constants;
import com.app.myplaces.intrastructure.error.OperationError;
import com.app.myplaces.intrastructure.util.NetworkUtil;

public class BaseRepository {

    public OperationError checkNetwork(NetworkUtil networkUtil) {
        final OperationError operationError = null;

        if (!networkUtil.isNetworkAvailable()) {
            operationError.setErrorType(Constants.ErrorType.NETWORK_ERROR);
        }
        return operationError;
    }
}
