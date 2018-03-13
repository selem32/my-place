package com.app.myplaces.service.repository;

import com.app.myplaces.intrastructure.Constants;
import com.app.myplaces.intrastructure.error.OperationError;
import com.app.myplaces.intrastructure.util.NetworkUtil;
import com.app.myplaces.intrastructure.util.ServiceListener;
import com.app.myplaces.service.Api;
import com.app.myplaces.service.model.location.Location;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository extends BaseRepository {

    private Api mApiInstance;
    private NetworkUtil mNetworkUtil;

    public LocationRepository(Api mApiInstance, NetworkUtil mNetworkUtil) {
        this.mApiInstance = mApiInstance;
        this.mNetworkUtil = mNetworkUtil;
    }

    public void requestLocationList(final ServiceListener<Location> callback) {
        OperationError networkError = checkNetwork(mNetworkUtil);

        if (networkError != null) {
            callback.onServiceError(networkError);
            return;
        }

        Call<Location> call = mApiInstance.getLocationList();
        call.enqueue(new Callback<Location>() {

            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                callback.onServiceSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                OperationError operationError = new OperationError();
                operationError.setErrorType(Constants.ErrorType.SERVICE_ERROR);
                callback.onServiceError(operationError);
            }
        });
    }
}
