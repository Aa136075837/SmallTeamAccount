package com.smallteam.smallteamaccount.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.base.MVPBaseFragment;
import com.smallteam.smallteamaccount.bean.NormalBean;
import com.smallteam.smallteamaccount.http.HttpObserver;
import com.smallteam.smallteamaccount.http.Load;
import com.smallteam.smallteamaccount.presenter.AddAccountContract;
import com.smallteam.smallteamaccount.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/1/4.
 */

public class AddAccountFragment extends MVPBaseFragment<AddAccountContract.AddAccountPresenter> implements AddAccountContract.AddAccountView {
    @BindView(R.id.test_et)
    EditText mTestEt;
    @BindView(R.id.send_code)
    Button mSendCode;
    @BindView(R.id.test_phone)
    EditText mTestPhone;
    @BindView(R.id.check_code)
    Button mCheckCode;
    private String mPhone;

    @Override
    protected void initEvents() {

        mSendCode.setOnClickListener(view -> {
            mPhone = mTestPhone.getText().toString().trim();
            if (StringUtil.strIsPhone(getActivity(), mPhone)) {
                mPresenter.sendCode("86", mPhone);
            }
        });

        mCheckCode.setOnClickListener(view -> {
            if(!StringUtil.strIsPhone(getActivity(), mPhone)){
                return;
            }
            RequestBody body = new FormBody.Builder()
                .add("phoneNum", mPhone)
                .add("code", mTestEt.getText().toString().trim()).build();
            HttpObserver<NormalBean> httpObserver = Load.createApi().niaBieLV(body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new HttpObserver<NormalBean>(getActivity()) {
                    @Override
                    protected void call(NormalBean value) {

                    }
                });
            addObservable(httpObserver);
        });
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
