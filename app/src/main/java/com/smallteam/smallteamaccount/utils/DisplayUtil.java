package com.smallteam.smallteamaccount.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.DisplayMetrics;

public class DisplayUtil {
    private static final String TAG = "DisplayUtil";

    public static int dip2px (Context context, float dipValue) {
        final float scale = context.getResources ().getDisplayMetrics ().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip (Context context, float pxValue) {
        final float scale = context.getResources ().getDisplayMetrics ().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getWindowHeight (Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager ().getDefaultDisplay ().getMetrics (metrics);
        return metrics.heightPixels;
    }

    public static int getWindowWidth (Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager ().getDefaultDisplay ().getMetrics (metrics);
        return metrics.widthPixels;
    }

    public static Point getScreenMetrics (Context context) {
        DisplayMetrics dm = context.getResources ().getDisplayMetrics ();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);
    }
    public static float getScreenRate(Context context) {
        Point P = getScreenMetrics(context);
        float H = P.y;
        float W = P.x;
        return (H / W);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getFontHeight(float fontSize) {
        Paint m_paint = new Paint();
        m_paint.setTextSize(fontSize);
        Paint.FontMetrics fm = m_paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.top) + 2;
    }
    public static int convertDpToPx(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}