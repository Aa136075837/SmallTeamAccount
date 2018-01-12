package com.smallteam.smallteamaccount.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smallteam.smallteamaccount.ui.view.ProgressDialogUtils;
import com.smallteam.smallteamaccount.utils.L;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by bo on 2018/1/3.
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    private CompositeDisposable mCompositeDisposable;
    private ProgressDialogUtils mProgressBar = null;
    private Unbinder mUnbinder;
    /**
     * 是否显示
     */
    protected boolean isVisible;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            /**
             * 根据保存的状态，hide或show farmgent
             */
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden()); //自己保存fragment 退出时的状态，防止fragment重叠
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutResId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
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
            L.e("BaseFragment RX 添加异常 ：：" + e.toString());
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isVisible = !hidden;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }

    protected abstract @LayoutRes
    int getLayoutResId();

    @Override
    public void showProgress() {
        mProgressBar = ProgressDialogUtils.getInstance(getActivity())
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

    @Override
    public void loadMsgOrError(int code, String msg) {
        if (mProgressBar != null) {
            mProgressBar.dismiss();
        }
    }
}
