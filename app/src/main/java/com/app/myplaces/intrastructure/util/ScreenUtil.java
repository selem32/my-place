package com.app.myplaces.intrastructure.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class ScreenUtil {
    public static Display getDisplay(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay();
    }

    public static int getScreenWidth(Context context) {
        Display display = getDisplay(context);
        Point size = new Point();
        display.getSize(size);

        return size.x;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void configureMarginTop(Context context, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && view != null) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(context), view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    public static void configureMarginTopFitsWindows(Context context, View toolbar, View view) {
        configureMarginTop(context, toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && view != null) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() - getStatusBarHeight(context), view.getPaddingRight(), view.getPaddingBottom());
        }
    }

}
