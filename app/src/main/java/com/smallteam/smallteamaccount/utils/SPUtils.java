package com.smallteam.smallteamaccount.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {
    private static final String SP_FILE_NAME = "SMALL";

    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    public static boolean getBoolean(Context context, String key, boolean value) {
        SharedPreferences sp =
                context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);

        return sp.getBoolean(key, value);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp =
                context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();

    }

    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    public static String getString(Context context, String key, String value) {
        SharedPreferences sp =
                context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, value);
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sp =
                context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static int getInt(Context context, String key) {
        return getInt(context, key, 0);

    }

    public static int getInt(Context context, String key, int value) {
        SharedPreferences sp =
                context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, value);
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences sp =
                context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }
}