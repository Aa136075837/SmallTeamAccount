package com.smallteam.smallteamaccount.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.base.MVPBaseFragment;
import com.smallteam.smallteamaccount.presenter.AddAccountContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/4.
 */

public class AddAccountFragment extends MVPBaseFragment<AddAccountContract.AddAccountPresenter> implements AddAccountContract.AddAccountView {

    @BindView(R.id.add_money_et)
    EditText mAddMoneyEt;
    @BindView(R.id.add_date_et)
    TextInputEditText mAddDateEt;
    @BindView(R.id.add_types_et)
    TextInputEditText mAddTypesEt;
    @BindView(R.id.add_parts_et)
    TextInputEditText mAddPartsEt;
    @BindView(R.id.textlayout)
    TextInputLayout mtextlayout;

    @Override
    protected void initEvents() {
        mAddDateEt.setOnClickListener(v -> {

        });
        mtextlayout.setPasswordVisibilityToggleEnabled(false);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected AddAccountContract.AddAccountPresenter createPresenter() {
        return new AddAccountContract.AddAccountPresenter(this, getActivity());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_add_account;
    }

}
