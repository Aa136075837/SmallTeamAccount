package com.smallteam.smallteamaccount.presenter;

import android.content.Context;

import com.smallteam.smallteamaccount.base.BasePresenter;
import com.smallteam.smallteamaccount.base.BaseView;

/**
 * Created by Administrator on 2018/1/4.
 */

public interface UserBalanceContract {
    interface UserBalanceView extends BaseView {

    }

    class UserBalancePresenter extends BasePresenter<UserBalanceView> {

        public UserBalancePresenter(UserBalanceView view, Context context) {
            super(view, context);
        }
    }
}
