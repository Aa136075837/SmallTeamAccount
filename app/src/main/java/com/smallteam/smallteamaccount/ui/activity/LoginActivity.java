package com.smallteam.smallteamaccount.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.bt_go)
    Button mBtGo;
    @BindView(R.id.cv)
    CardView mCv;
    @BindView(R.id.tv_login_getCode)
    TextView getCodeTv;
    private CountDownTimer timer;

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initParams(Bundle bundle) {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        mBtGo.setOnClickListener(v -> {
            login();
//            loginSuccess();
        });
        getCodeTv.setOnClickListener(view -> getCode());
    }

    private void getCode() {
        String username = mEtUsername.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            EasyToast.showShort(this, R.string.toast_username_not_null);
            return;
        }
        //获取验证码，同时进行60s倒计时
        enableCodeUi();
        mPresenter.getCode(username);
    }

    private void login() {
        String username = mEtUsername.getText().toString().trim();
        String verifyCode = mEtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            EasyToast.showShort(this, R.string.toast_username_not_null);
            return;
        }
        if (TextUtils.isEmpty(verifyCode)) {
            EasyToast.showShort(this, R.string.toast_verify_code_not_null);
            return;
        }

        mPresenter.login(username, verifyCode);
    }

    @Override
    protected LoginContract.LoginPresenter createPresenter() {
        return new LoginContract.LoginPresenter(this, this);
    }

    @Override
    public void loginSuccess() {
        startAcvitity(MainActivity.class);
        finish();
    }

    @Override
    public void loginFailed() {
        getCodeTv.setEnabled(true);
    }

    @Override
    public void loadMsgOrError(int code, String msg) {
        super.loadMsgOrError(code, msg);
        EasyToast.showShort(this, code + " : " + msg);
    }

    private void enableCodeUi() {
        getCodeTv.setEnabled(false);
        timer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                getCodeTv.setText(l / 1000 + "后重发");
            }

            @Override
            public void onFinish() {
                getCodeTv.setText("获取验证码");
                getCodeTv.setEnabled(true);
            }
        };
        timer.start();
    }

    /*处理获取验证码失败的情况*/
    @Override
    public void dealVerifyCodeFail() {
        timer.cancel();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getCodeTv.setText("获取验证码");
                getCodeTv.setEnabled(true);
            }
        });

    }
}
