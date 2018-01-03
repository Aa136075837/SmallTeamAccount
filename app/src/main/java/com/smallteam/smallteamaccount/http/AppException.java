package com.smallteam.smallteamaccount.http;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2018/1/3.
 */

public class AppException {

    public static final int NET_WORK_ERROR = 0x111;
    public static final String NET_WORK_ERROE_MSG = "网络出错了...";

    public static final int SERVICE_ERROR = 0x112;
    public static final String SERVICE_ERROR_MSG = "服务器出错了...";

    public static final int NET_TIME_OUT = 0x113;
    public static final String NET_TIME_OUT_MSG = "网络超时...";

    private static void loadError(Context context, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Observable.just(msg).observeOn(AndroidSchedulers.mainThread()).subscribe(s -> Toast.makeText(context, s, Toast.LENGTH_SHORT).show());
        }
    }

    public static void handleException(Context context, Throwable e) {
        if (e instanceof UnknownHostException || e instanceof ConnectException) {
            loadError(context, NET_WORK_ERROE_MSG);
        } else if (e instanceof ServiceException) {
            loadError(context, ((ServiceException) e).getMsg());
        } else if (e instanceof SocketTimeoutException) {
            loadError(context, NET_TIME_OUT_MSG);
        }
    }
}
