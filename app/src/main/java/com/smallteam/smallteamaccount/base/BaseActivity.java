package com.smallteam.smallteamaccount.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.ui.view.ProgressDialogUtils;
import com.smallteam.smallteamaccount.utils.L;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseActivity extends AppCompatActivity implements BaseView {

    private CompositeDisposable mCompositeDisposable;
    private ProgressDialogUtils mProgressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private CompositeDisposable getCompositeDisposable() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        return mCompositeDisposable;
    }

    private void unSubscribe() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = getCompositeDisposable();
        }
        mCompositeDisposable.clear();
    }

    protected void addObservable(Disposable d) {
        try {
            if (mCompositeDisposable == null) {
                mCompositeDisposable = getCompositeDisposable();
            }
            mCompositeDisposable.add(d);
        } catch (Exception e) {
            L.e("BaseActivity RX 添加异常 ：：" + e.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }

    protected void toTargetAcvitity(Class<?> target) {
        startActivity(new Intent(this, target));
    }

    protected void toTargetAcvitity(Class<?> target, Bundle bundle) {
        Intent intent = new Intent(this, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        mProgressBar = ProgressDialogUtils.getInstance(this)
            .setCancelAble(false)
            .setCanceledOutside(false)
            .showProgress();
    }

    @Override
    public void loadError() {
        if (mProgressBar != null) {
            mProgressBar.dismiss();
        }
    }

    @Override
    public void loadFinish() {
        if (mProgressBar != null) {
            mProgressBar.dismiss();
        }
    }
}
