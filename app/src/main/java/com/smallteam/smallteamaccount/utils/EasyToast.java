package com.smallteam.smallteamaccount.utils;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

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

    public static void showInThread(Context context, String msg, @Length int length) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast toast = getToast(context);
                toast.setText(msg);
                toast.setDuration(length);
                toast.show();
                Looper.loop();
            }
        }.start();
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
