package com.smallteam.smallteamaccount.ui.fragment;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.base.MVPBaseFragment;
import com.smallteam.smallteamaccount.presenter.UserBalanceContract;

/**
 * Created by Administrator on 2018/1/4.
 */

public class UserBalanceFragment extends MVPBaseFragment<UserBalanceContract.UserBalancePresenter> {
    @Override
    protected void initEvents() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected UserBalanceContract.UserBalancePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_user_balance;
    }
}
