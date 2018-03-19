package com.app.myplaces.intrastructure.util;


import android.content.Context;
import android.util.Log;

import com.app.myplaces.service.model.image.ImageList;
import com.app.myplaces.service.model.review.Review;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;


public final class MockUtil {
    public static final String TAG = "MockUtil";

    public ImageList getImageList(Context context) {
        Gson gson = new Gson();
        String fileName = "json/images.json";
        String jsonOutputEffectuation = writeStrFileAsset(context, fileName);
        Type type = new TypeToken<ImageList>() {
        }.getType();
        return gson.fromJson(jsonOutputEffectuation, type);
    }

    public Review getReviewList(Context context) {
        Gson gson = new Gson();
        String fileName = "json/reviews.json";
        String jsonOutputEffectuation = writeStrFileAsset(context, fileName);
        Type type = new TypeToken<Review>() {
        }.getType();
        return gson.fromJson(jsonOutputEffectuation, type);
    }

    private String writeStrFileAsset(Context context, String fileName) {
        InputStream is;

        try {
            is = context.getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];
            while (true) {
                int oneByte = is.read(buffer);
                if (oneByte < 0) {
                    break;
                }
            }

            is.close();

            return new String(buffer);
        } catch (IOException e) {
            Log.e(TAG, getClass().getSimpleName() + e.toString(), e);

            return null;
        }
    }
}
