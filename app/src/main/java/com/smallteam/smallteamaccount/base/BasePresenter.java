package com.smallteam.smallteamaccount.base;

import android.content.Context;

import com.smallteam.smallteamaccount.bean.NormalBean;
import com.smallteam.smallteamaccount.http.HttpObserver;
import com.smallteam.smallteamaccount.http.Load;
import com.smallteam.smallteamaccount.utils.L;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/1/3.
 */

public class BasePresenter<V extends BaseView> implements AbBasePresenter {
    protected V mView;
    protected Context mContext;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(V view, Context context) {
        mView = view;
        mContext = context;
    }

    private CompositeDisposable getCompositeDisposable() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        return mCompositeDisposable;
    }

    private void unSubscribe() {
        try {
            if (mCompositeDisposable != null) {
                mCompositeDisposable.clear();
            }
        } catch (Exception e) {
            L.e("BasePresenter RX 注销异常 ：：" + e.toString());
        }
    }

    public void addObservable(Disposable d) {
        try {
            if (mCompositeDisposable == null) {
                mCompositeDisposable = getCompositeDisposable();
            }
            mCompositeDisposable.add(d);
        } catch (Exception e) {
            L.e("BasePresenter RX 添加异常 ：：" + e.toString());
        }
    }

    @Override
    public void onDestroy() {
        unSubscribe();
        mView = null;
        mContext = null;
    }
}
