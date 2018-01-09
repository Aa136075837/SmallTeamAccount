package com.smallteam.smallteamaccount.presenter;

import android.content.Context;
import com.smallteam.smallteamaccount.base.BasePresenter;
import com.smallteam.smallteamaccount.base.BaseView;
import com.smallteam.smallteamaccount.bean.NormalBean;
import com.smallteam.smallteamaccount.http.HttpObserver;
import com.smallteam.smallteamaccount.http.Load;
import com.smallteam.smallteamaccount.http.RequestBodyUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.LinkedHashMap;
import okhttp3.RequestBody;

/**
 * Created by shuangyue on 2018/1/6.
 */
public interface LoginContract {
    interface LoginView extends BaseView{
        void loginSuccess();
        void loginFailed();
    }
    class LoginPresenter extends BasePresenter<LoginView>{

        public LoginPresenter (LoginView view, Context context) {
            super (view, context);
        }

        public void login(LinkedHashMap<String,Object> map){
            RequestBody body = RequestBodyUtils.getParams (map);
            HttpObserver<NormalBean> httpObserver = Load.createApi ()
                .login (body)
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribeWith (new HttpObserver<NormalBean> (mContext) {
                    @Override protected void call (NormalBean value) {
                        mView.loginSuccess ();
                    }

                    @Override public void onError (Throwable e) {
                        super.onError (e);
                        mView.loginFailed ();
                    }
                });
            addObservable (httpObserver);
        }
    }
}
