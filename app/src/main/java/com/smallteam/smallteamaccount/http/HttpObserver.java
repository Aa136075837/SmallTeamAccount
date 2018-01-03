package com.smallteam.smallteamaccount.http;

import android.content.Context;

import com.smallteam.smallteamaccount.base.BaseView;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Administrator on 2018/1/3.
 */

public abstract class HttpObserver<T> extends DisposableObserver<T> {
    private BaseView mView;
    private Context mContext;
    private boolean isShowLoading;

    public HttpObserver(Context context) {
        mContext = context;
    }

    public HttpObserver(Context context, BaseView view) {
        mView = view;
        mContext = context;
    }

    public HttpObserver(Context context, BaseView view, boolean isShowLoading) {
        mView = view;
        mContext = context;
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
        if (mView != null) {
            mView.loadFinish();
        }
        if (value == null) {
            onError(new ServiceException(AppException.SERVICE_ERROR, AppException.SERVICE_ERROR_MSG));
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
        if (mView != null) {
            mView.loadError();
        }
        if (mContext != null) {
            AppException.handleException(mContext, e);
        }
    }

    @Override
    public void onComplete() {

    }
}
