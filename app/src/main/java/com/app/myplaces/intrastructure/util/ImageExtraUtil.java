package com.app.myplaces.intrastructure.util;

import android.graphics.Bitmap;

public class ImageExtraUtil {
    private static ImageExtraUtil instance;
    private Bitmap imageLocation;

    public static synchronized ImageExtraUtil getInstance() {
        if (instance == null) {
            instance = new ImageExtraUtil();
        }
        return instance;
    }

    public Bitmap getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(Bitmap imageLocation) {
        this.imageLocation = imageLocation;
    }
}
