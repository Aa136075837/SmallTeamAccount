package com.smallteam.smallteamaccount.ui.activity;

import android.os.Bundle;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.base.MVPBaseActivity;
import com.smallteam.smallteamaccount.presenter.CreateBooksAccountContract;

/**
 * 创建账本
 * Created by TENGFEI on 2018/1/18.
 */
public class CreateBookAccountActivity extends MVPBaseActivity<CreateBooksAccountContract.CreateBooksAccountPresenter>
    implements CreateBooksAccountContract.CreateAccountView{
    @Override
    protected int initLayout() {
        return R.layout.activity_create_account;
    }

    @Override
    protected void initParams(Bundle bundle) {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected CreateBooksAccountContract.CreateBooksAccountPresenter createPresenter() {
        return new CreateBooksAccountContract.CreateBooksAccountPresenter(this,this);
    }
}
