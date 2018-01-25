package com.smallteam.smallteamaccount.presenter;

import android.content.Context;

import com.smallteam.smallteamaccount.base.BasePresenter;
import com.smallteam.smallteamaccount.base.BaseView;
import com.smallteam.smallteamaccount.bean.GroupBean;
import com.smallteam.smallteamaccount.bean.NewAccountBean;
import com.smallteam.smallteamaccount.http.HttpObserver;
import com.smallteam.smallteamaccount.http.Load;
import com.smallteam.smallteamaccount.http.RequestBodyUtils;
import com.smallteam.smallteamaccount.utils.SpConfig;

import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/1/3.
 */

public interface MainContract {
    interface MainView extends BaseView {
        void textSuccess();

        void setGroupList(List<GroupBean> groupBeans);

        /**
         * 添加账单成功
         * @param value
         */
        void addAccountSuccess(NewAccountBean value);
    }

    /**
     * @author Macx
     */
    class MainPresenter extends BasePresenter<MainView> {

        public MainPresenter(MainView view, Context context) {
            super(view, context);
        }

        public void getGroups() {

            String id = SpConfig.getInstance().getUser().getId();
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("id", id);
            RequestBody body = RequestBodyUtils.getParams(map);

            HttpObserver<List<GroupBean>> httpObserver = Load.call(Load.createApi().getGroups(body))
                    .subscribeWith(new HttpObserver<List<GroupBean>>(mView, true) {
                @Override
                protected void call(List<GroupBean> value) {
                    mView.setGroupList(value);
                }
            });
            addObservable(httpObserver);
        }


    }

}
