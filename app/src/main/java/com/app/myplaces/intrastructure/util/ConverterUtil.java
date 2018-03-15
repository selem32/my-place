package com.app.myplaces.intrastructure.util;

import android.content.Context;
import android.content.res.Resources;

public class ConverterUtil {

    public static float convertDpToPx(Context context, float dp) {
        Resources res = context.getResources();
        return dp * (res.getDisplayMetrics().densityDpi / 160f);
    }

    public static int getPxFromDp(Context context, int ressourceId) {
        return (int) ConverterUtil.convertDpToPx(context, context.getResources().getDimension(ressourceId));
    }
}
