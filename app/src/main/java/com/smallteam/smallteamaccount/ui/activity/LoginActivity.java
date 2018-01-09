package com.smallteam.smallteamaccount.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.base.MVPBaseActivity;
import com.smallteam.smallteamaccount.presenter.LoginContract;
import com.smallteam.smallteamaccount.utils.EasyToast;

/**
 * Created by shuangyue on 2018/1/6.
 */
public class LoginActivity extends MVPBaseActivity<LoginContract.LoginPresenter>
    implements LoginContract.LoginView {
    @BindView (R.id.et_username) EditText mEtUsername;
    @BindView (R.id.et_password) EditText mEtPassword;
    @BindView (R.id.bt_go) Button mBtGo;
    @BindView (R.id.cv) CardView mCv;
    @BindView(R.id.tv_login_getCode)
    TextView getCodeTv;

    @Override protected int initLayout () {
        return R.layout.activity_login;
    }

    @Override protected void initParams (Bundle bundle) {

    }

    @Override protected void initViews () {

    }

    @Override protected void initEvents () {
        mBtGo.setOnClickListener (v -> login ());
        getCodeTv.setOnClickListener(view -> getCode());
    }

    private void getCode() {
        String username = mEtUsername.getText ().toString ().trim ();
        if(TextUtils.isEmpty (username) ){
            EasyToast.showShort (this,R.string.toast_username_not_null);
            return;
        }
        mPresenter.getCode(username);
    }

    private void login () {
        String username = mEtUsername.getText ().toString ().trim ();
        String verifyCode = mEtPassword.getText ().toString ().trim ();
        if(TextUtils.isEmpty (username) ){
            EasyToast.showShort (this,R.string.toast_username_not_null);
            return;
        }
        if(TextUtils.isEmpty (verifyCode)){
            EasyToast.showShort (this,R.string.toast_verify_code_not_null);
           return;
        }

        mPresenter.login (username,verifyCode);
    }

    @Override protected LoginContract.LoginPresenter createPresenter () {
        return new LoginContract.LoginPresenter (this, this);
    }

    @Override public void loginSuccess () {
        startAcvitity (MainActivity.class);
        finish ();
    }

    @Override public void loginFailed () {

    }

}
