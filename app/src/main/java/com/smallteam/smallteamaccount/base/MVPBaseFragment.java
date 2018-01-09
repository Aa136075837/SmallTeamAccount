package com.smallteam.smallteamaccount.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bo on 2018/1/3.
 */
public abstract class MVPBaseFragment<P extends BasePresenter> extends BaseFragment {

    protected P mPresenter;

    protected abstract P createPresenter();

    protected abstract void initViews();

    protected abstract void initEvents();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mPresenter = createPresenter();
        initViews();
        initEvents();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.onDestroy();
        }
    }

}
