package com.smallteam.smallteamaccount.http;

import android.content.Context;
import android.content.SharedPreferences;

import com.smallteam.smallteamaccount.base.BaseView;
import com.smallteam.smallteamaccount.constant.SysConstant;
import com.smallteam.smallteamaccount.utils.EasyToast;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Administrator on 2018/1/3.
 */

public abstract class HttpObserver<T> extends DisposableObserver<T> {
    private BaseView mView;
    private Context mContext;
    private boolean isShowLoading;

    /**
     * 如果 页面 不需要自己处理异常信息，也不需要 显示加载进度条，就用该构造器
     */
    public HttpObserver(Context context) {
        mContext = context;
    }

    /**
     * 如果 页面 要自己处理异常信息， 但 不需要 显示加载进度条，就用该构造器
     */
    public HttpObserver(BaseView view) {
        mView = view;
    }

    /**
     * 如果 页面 即 需要自己处理异常，又 需要 显示加载进度条，就用该构造器
     */
    public HttpObserver(BaseView view, boolean isShowLoading) {
        mView = view;
        this.isShowLoading = isShowLoading;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (this.isShowLoading && mView != null) {
            mView.showProgress();
        }
    }

    @Override
    public void onNext(T value) {
        if (mView != null && isShowLoading) {
            mView.loadFinish();
        }
        if (value == null) {
            onError(ServiceException.create(AppException.SERVICE_ERROR, AppException.SERVICE_ERROR_MSG));
        } else {
            call(value);
        }
    }

    /**
     * 请求成功的回调
     */
    protected abstract void call(T value);

    @Override
    public void onError(Throwable e) {
        if (mView != null && isShowLoading) {
            mView.loadError();
        }
        if (mView != null) {
            AppException.handleExceptionByView(mView, e);
        } else if (mContext != null) {
            AppException.handleExceptionByContext(mContext, e);
        }
    }

    @Override
    public void onComplete() {

    }
}
