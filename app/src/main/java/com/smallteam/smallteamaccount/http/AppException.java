package com.smallteam.smallteamaccount.http;

import android.content.Context;
import android.text.TextUtils;

import com.smallteam.smallteamaccount.base.BaseView;
import com.smallteam.smallteamaccount.utils.EasyToast;
import com.smallteam.smallteamaccount.utils.L;

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
    public static final String NET_WORK_ERROR_MSG = "网络出错了...";

    public static final int SERVICE_ERROR = 0x112;
    public static final String SERVICE_ERROR_MSG = "服务器出错了...";

    public static final int NET_TIME_OUT = 0x113;
    public static final String NET_TIME_OUT_MSG = "网络超时...";

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private static void loadError(Context context, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Observable.just(msg).observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
                EasyToast.showShort(context, msg);
                L.e(msg);
            });
        }
    }

    /**
     * 通过view 处理异常，各个页面可以实现
     * @param view
     * @param e
     */
    public static void handleExceptionByView(BaseView view, Throwable e) {
        if (e instanceof UnknownHostException || e instanceof ConnectException) {
            view.loadMsgOrError(NET_WORK_ERROR, NET_WORK_ERROR_MSG);
        } else if (e instanceof ServiceException) {
            view.loadMsgOrError(((ServiceException) e).getCode(), ((ServiceException) e).getMsg());
        } else if (e instanceof SocketTimeoutException) {
            view.loadMsgOrError(NET_TIME_OUT, NET_TIME_OUT_MSG);
        }
    }

    /**
     * 通过Context处理异常
     * @param context
     * @param e
     */
    public static void handleExceptionByContext(Context context, Throwable e) {
        if (e instanceof UnknownHostException || e instanceof ConnectException) {
            loadError(context, NET_WORK_ERROR_MSG);
        } else if (e instanceof ServiceException) {
            loadError(context, ((ServiceException) e).getMsg());
        } else if (e instanceof SocketTimeoutException) {
            loadError(context, NET_TIME_OUT_MSG);
        }
    }
}
