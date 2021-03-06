package com.smallteam.smallteamaccount.utils;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.smallteam.smallteamaccount.base.SmallTeamApp;

/**
 * Created by Administrator on 2018/1/4.
 */

public class EasyToast {
    @IntDef({Toast.LENGTH_LONG, Toast.LENGTH_SHORT})
    private @interface Length {

    }

    private static Toast mToast;

    private static Toast getToast(Context context) {
        if (mToast == null) {
            synchronized (EasyToast.class) {
                if (mToast == null) {
                    mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
                }
            }
        }
        return mToast;
    }

    /**
     * 子线程中 显示Toast，默认短时间显示
     */
    public static void showInThread(Context context, String msg) {
        showInThread(context, msg, Toast.LENGTH_SHORT);
    }
    public static void showInThread(Context context, @StringRes int msg) {
        showInThread(context, msg, Toast.LENGTH_SHORT);
    }

    public static void showInThread(Context context, String msg, @Length int length) {
        SmallTeamApp.getHandler().post(new Runnable() {
            @Override
            public void run() {
                Toast toast = getToast(context);
                toast.setText(msg);
                toast.show();
            }
        });
    }

    public static void showInThread(Context context, @StringRes int msg, @Length int length) {
        SmallTeamApp.getHandler().post(new Runnable() {
            @Override
            public void run() {
                Toast toast = getToast(context);
                toast.setText(msg);
                toast.show();
            }
        });
    }

    public static void showShort(@NonNull Context context, @StringRes int stringId) {
        Toast toast = getToast(context);
        toast.setText(stringId);
        toast.show();
    }

    public static void showShort(@NonNull Context context, String string) {
        Toast toast = getToast(context);
        toast.setText(string);
        toast.show();
    }

}
