package com.smallteam.smallteamaccount.presenter;

import android.content.Context;

import com.smallteam.smallteamaccount.base.BasePresenter;
import com.smallteam.smallteamaccount.base.BaseView;
import com.smallteam.smallteamaccount.bean.GroupBean;
import com.smallteam.smallteamaccount.bean.NormalBean;
import com.smallteam.smallteamaccount.http.HttpObserver;
import com.smallteam.smallteamaccount.http.Load;
import com.smallteam.smallteamaccount.http.RequestBodyUtils;
import com.smallteam.smallteamaccount.utils.SpConfig;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
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

        public MainPresenter(MainView view, Context context) {
            super(view, context);
        }

        public void getGroups() {

            String id = SpConfig.getInstance().getUser().getId();
            RequestBody body = RequestBodyUtils.getParams((LinkedHashMap<String, Object>) new LinkedHashMap<>().put("id", id));

            HttpObserver<List<GroupBean>> httpObserver = Load.call(Load.createApi().getGroups(body)).subscribeWith(new HttpObserver<List<GroupBean>>(mView, true) {
                @Override
                protected void call(List<GroupBean> value) {

                }
            });
            addObservable(httpObserver);
        }
    }

}
