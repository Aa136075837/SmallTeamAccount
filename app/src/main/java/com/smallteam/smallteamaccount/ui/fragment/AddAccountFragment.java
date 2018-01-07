package com.smallteam.smallteamaccount.ui.fragment;

import android.widget.Button;
import android.widget.EditText;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.base.MVPBaseFragment;
import com.smallteam.smallteamaccount.bean.NormalBean;
import com.smallteam.smallteamaccount.http.HttpObserver;
import com.smallteam.smallteamaccount.http.Load;
import com.smallteam.smallteamaccount.presenter.AddAccountContract;

import butterknife.BindView;
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

    @Override
    protected void initEvents() {
        mPresenter.sendCode("86", "17688163211");

        mSendCode.setOnClickListener(view -> {
            RequestBody body = new FormBody.Builder()
                    .add("phoneNum", "17688163211")
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
