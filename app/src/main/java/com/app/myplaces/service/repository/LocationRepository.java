package com.app.myplaces.service.repository;

import com.app.myplaces.intrastructure.util.NetworkUtil;
import com.app.myplaces.service.Api;

public class LocationRepository extends BaseRepository {

    private Api mApiInstance;
    private NetworkUtil mNetworkUtil;

    public LocationRepository(Api mApiInstance, NetworkUtil mNetworkUtil) {
        this.mApiInstance = mApiInstance;
        this.mNetworkUtil = mNetworkUtil;
    }
}
