package com.smallteam.smallteamaccount.presenter;

import android.content.Context;

import com.smallteam.smallteamaccount.base.BasePresenter;
import com.smallteam.smallteamaccount.base.BaseView;
import com.smallteam.smallteamaccount.bean.NormalBean;
import com.smallteam.smallteamaccount.http.HttpObserver;
import com.smallteam.smallteamaccount.http.Load;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/1/3.
 */

public interface MainContract {
    interface MainView extends BaseView {
        void textSuccess();
    }

    class MainPresenter extends BasePresenter<MainView> {
        private MainView mMainView;

        public MainPresenter(MainView view, Context context) {
            super(view, context);
            mMainView = view;
        }

        public void test() {
            RequestBody body = new FormBody.Builder()
                .add("password", "asdd45645")
                .add("phone", "14785203693")
                .build();
            HttpObserver<NormalBean> httpObserver = Observable.zip(Load.createApi().test(body), Observable.timer(3, TimeUnit.SECONDS), new BiFunction<NormalBean, Long, NormalBean>() {
                @Override
                public NormalBean apply(NormalBean normalBean, Long aLong) throws Exception {
                    return normalBean;
                }
            }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new HttpObserver<NormalBean>(mContext, mMainView, true) {
                    @Override
                    protected void call(NormalBean value) {
                        mView.textSuccess();
                    }
                });
            addObservable(httpObserver);
        }
    }

}
