package com.smallteam.smallteamaccount.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import butterknife.ButterKnife;

public abstract class MVPBaseActivity<P extends AbBasePresenter> extends BaseActivity {
    protected P mPresenter;

    protected abstract @LayoutRes
    int initLayout();

    /**
     * 分发参数
     */
    protected abstract void initParams(Bundle bundle);

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 初始化事件
     */
    protected abstract void initEvents();

    /**
     * 初始化 Presenter
     */
    protected abstract P createPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(initLayout());
        ButterKnife.bind (this);
        mPresenter = createPresenter();
        initParams(bundle);
        initViews();
        initEvents();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
