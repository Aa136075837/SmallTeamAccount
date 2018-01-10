package com.smallteam.smallteamaccount.presenter;

import android.content.Context;
import android.widget.Toast;

import com.smallteam.smallteamaccount.base.BasePresenter;
import com.smallteam.smallteamaccount.base.BaseView;
import com.smallteam.smallteamaccount.bean.NormalBean;
import com.smallteam.smallteamaccount.bean.UserBean;
import com.smallteam.smallteamaccount.http.HttpObserver;
import com.smallteam.smallteamaccount.http.Load;
import com.smallteam.smallteamaccount.http.RequestBodyUtils;
import com.smallteam.smallteamaccount.utils.EasyToast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
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
        void dealVerifiyCodeFail();
    }
    class LoginPresenter extends BasePresenter<LoginView>{

        public LoginPresenter (LoginView view, Context context) {
            super (view, context);
        }

        public void login(String phoneNum,String verfiyCode){

            LinkedHashMap<String,Object> map = new LinkedHashMap<>();
            map.put("phoneNum",phoneNum);
            map.put("code",verfiyCode);

            HttpObserver<UserBean> httpObserver = Load.call(Load.createApi()
                .login(RequestBodyUtils.getParams(map))).subscribeWith(new HttpObserver<UserBean>(mContext) {
                @Override
                protected void call(UserBean value) {
                    mView.loginSuccess();
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    mView.loginFailed();
                }
            });
            addObservable (httpObserver);
        }

        public void getCode(String phone){
            // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
                // 注册一个事件回调，用于处理发送验证码操作的结果
                SMSSDK.registerEventHandler(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理成功得到验证码的结果
                            // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
//                            Toast.makeText(mContext, "验证码已发送", Toast.LENGTH_SHORT).show();
                            EasyToast.showInThread(mContext, "验证码已发送", Toast.LENGTH_LONG);
                        } else {
                            // TODO 处理错误的结果
                            mView.dealVerifiyCodeFail();
//                            Toast.makeText(mContext, "请求验证码出错", Toast.LENGTH_SHORT).show();
                            EasyToast.showInThread(mContext, "请求验证码出错", Toast.LENGTH_LONG);
                        }

                        // 用完回调要注销，否则会造成泄露
                        SMSSDK.unregisterEventHandler(this);
                    }
                });
                // 触发操作
                SMSSDK.getVerificationCode("86", phone);
            }
    }
}
