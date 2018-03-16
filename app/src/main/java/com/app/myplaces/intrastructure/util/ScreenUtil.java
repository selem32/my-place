package com.app.myplaces.intrastructure.util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
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

}
