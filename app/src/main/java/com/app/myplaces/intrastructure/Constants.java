package com.app.myplaces.intrastructure;

public class Constants {

    public static final String BASE_API = "https://hotmart-mobile-app.herokuapp.com/";
    public static final String ARGUMENT_LOCATION_ITEM = "ARGUMENT_LOCATION_ITEM";
    public static final String ARGUMENT_IMAGE_ITEM = "ARGUMENT_IMAGE_ITEM";

    public enum ErrorType {
        NETWORK_ERROR, SERVICE_ERROR, GENERIC_ERROR, CUSTOM_ERROR
    }
}
