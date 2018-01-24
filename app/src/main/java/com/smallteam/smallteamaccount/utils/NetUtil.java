package com.smallteam.smallteamaccount.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 *
 * @author TENGFEI
 * @date 2018/1/9
 */
public class NetUtil {

    public static final int NETWORN_NONE = 0;
    public static final int NETWORN_WIFI = 1;
    public static final int NETWORN_MOBILE = 2;

    /**获取当前网络是wife还是3G还是没有网络*/
    public static int getNetworkState(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            switch (info.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    return NETWORN_WIFI;
                case ConnectivityManager.TYPE_MOBILE:
                    return NETWORN_MOBILE;
                default:
                    break;
            }
        }
        return NETWORN_NONE;
    }
}
