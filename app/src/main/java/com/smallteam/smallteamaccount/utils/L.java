package com.smallteam.smallteamaccount.utils;

import android.util.Log;

import com.smallteam.smallteamaccount.BuildConfig;

/**
 * Created by Administrator on 2018/1/3.
 */

public class L {
    private L() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static boolean isDebug = BuildConfig.DEBUG;
    private static String TAG = "SMALLTEAMACCOUNT";

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    public static void w(String msg) {
        if (isDebug)
            Log.w(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (isDebug)
            Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }
}
