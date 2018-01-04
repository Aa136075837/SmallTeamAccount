package com.smallteam.smallteamaccount.presenter;

import android.content.Context;

import com.smallteam.smallteamaccount.base.BasePresenter;
import com.smallteam.smallteamaccount.base.BaseView;

/**
 * Created by Administrator on 2018/1/4.
 */

public interface AddAccountContract {
    interface AddAccountView extends BaseView {

    }

    class AddAccountPresenter extends BasePresenter<AddAccountView> {

        public AddAccountPresenter(AddAccountView view, Context context) {
            super(view, context);
        }
    }
}
