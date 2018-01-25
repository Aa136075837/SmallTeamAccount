package com.smallteam.smallteamaccount.presenter;

import android.content.Context;

import com.smallteam.smallteamaccount.base.BasePresenter;
import com.smallteam.smallteamaccount.base.BaseView;
import com.smallteam.smallteamaccount.bean.NewAccountBean;
import com.smallteam.smallteamaccount.http.HttpObserver;
import com.smallteam.smallteamaccount.http.Load;
import com.smallteam.smallteamaccount.http.RequestBodyUtils;

import java.util.LinkedHashMap;

import okhttp3.RequestBody;

/**
 * @author Macx
 * @date 2018/1/25.
 */
public interface AddGroupContract {
    interface AddGroupView extends BaseView{
        /**
         * 添加账本成功
         * @param value
         */
        void addAccountSuccess(NewAccountBean value);
    }

    class AddGroupPresenter extends BasePresenter<AddGroupView>{

        public AddGroupPresenter(AddGroupView view, Context context) {
            super(view, context);
        }

        public void addOneAccount(LinkedHashMap<String,Object> map) {

            RequestBody body = RequestBodyUtils.getParams(map);
            Load.call(Load.createApi().addOneAccount(body))
                    .subscribeWith(new HttpObserver<NewAccountBean>(mView,true) {
                        @Override
                        protected void call(NewAccountBean value) {
                            mView.addAccountSuccess(value);
                        }
                    });

        }
    }
}
