package com.app.myplaces.intrastructure;

import android.app.Application;

import com.app.myplaces.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyPlaceApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        configCalligraphy();
    }

    private void configCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
