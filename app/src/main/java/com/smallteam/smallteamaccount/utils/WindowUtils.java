package com.smallteam.smallteamaccount.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by Administrator on 2018/1/10.
 */

public class WindowUtils {
    private Activity mContext;
    private int mScreenHeight;
    private final Rect mRect;
    private static WindowUtils mInstance;
    private int mRealHeight;

    public static WindowUtils getInstance(Activity context) {
        mInstance = new WindowUtils(context);
        return mInstance;
    }

    private WindowUtils(Activity context) {
        mContext = context;
        //获取当前屏幕内容的高度
        mScreenHeight = mContext.getWindow().getDecorView().getHeight();

        //获取View可见区域的bottom
        mRect = new Rect();
        mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(mRect);
    }

    /**
     * 获取当前可显示的高度（除去底部虚拟按钮高度）
     *
     * @return
     */
    public int getDisplayHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Log.e("WangJ", "屏幕高:" + dm.heightPixels);

        //应用区域
        Rect outRect1 = new Rect();
        mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);

        return outRect1.height();
    }

    /**
     * 软键盘是否显示
     *
     * @return
     */
    public boolean isSoftShowing() {
        return mScreenHeight - mRect.bottom - getSoftButtonsBarHeight() != 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private int getSoftButtonsBarHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        mContext.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        mContext.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        mRealHeight = metrics.heightPixels;
        if (mRealHeight > usableHeight) {
            return mRealHeight - usableHeight;
        } else {
            return 0;
        }
    }
}
