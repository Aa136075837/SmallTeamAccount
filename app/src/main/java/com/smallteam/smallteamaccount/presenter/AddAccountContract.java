package com.smallteam.smallteamaccount.presenter;

import android.content.Context;
import android.widget.Toast;

import com.smallteam.smallteamaccount.base.BasePresenter;
import com.smallteam.smallteamaccount.base.BaseView;
import com.smallteam.smallteamaccount.utils.EasyToast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2018/1/4.
 */

public interface AddAccountContract {
    interface AddAccountView extends BaseView {

    }

    class AddAccountPresenter extends BasePresenter<AddAccountView> {

        public AddAccountPresenter(AddAccountView view, Context context) {
            super(view, context);
        }

        // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
        public void sendCode(String country, String phone) {
            // 注册一个事件回调，用于处理发送验证码操作的结果
            SMSSDK.registerEventHandler(new EventHandler() {
                @Override
                public void afterEvent(int event, int result, Object data) {
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        // TODO 处理成功得到验证码的结果
                        // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                        EasyToast.showInThread(mContext, "验证码已发送", Toast.LENGTH_LONG);
                    } else {
                        // TODO 处理错误的结果
                        EasyToast.showInThread(mContext, "请求验证码出错", Toast.LENGTH_LONG);
                    }

                    // 用完回调要注销，否则会造成泄露
                    SMSSDK.unregisterEventHandler(this);
                }
            });
            // 触发操作
            SMSSDK.getVerificationCode(country, phone);
        }

        // 提交验证码，其中的code表示验证码，如“1357”
        public void submitCode(String country, String phone, String code) {
            // 注册一个事件回调，用于处理提交验证码操作的结果
            SMSSDK.registerEventHandler(new EventHandler() {
                @Override
                public void afterEvent(int event, int result, Object data) {
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        // TODO 处理验证成功的结果
                    } else {
                        // TODO 处理错误的结果
                    }

                    // 用完回调要注销，否则会造成泄露
                    SMSSDK.unregisterEventHandler(this);
                }
            });
            // 触发操作
            SMSSDK.submitVerificationCode(country, phone, code);
        }
    }
}
