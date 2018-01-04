package com.smallteam.smallteamaccount.ui.fragment;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.base.MVPBaseFragment;
import com.smallteam.smallteamaccount.presenter.AddAccountContract;

/**
 * Created by Administrator on 2018/1/4.
 */

public class AddAccountFragment extends MVPBaseFragment<AddAccountContract.AddAccountPresenter> implements AddAccountContract.AddAccountView {
    @Override
    protected void initEvents() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected AddAccountContract.AddAccountPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_add_account;
    }
}
