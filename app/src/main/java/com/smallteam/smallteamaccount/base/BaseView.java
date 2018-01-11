package com.smallteam.smallteamaccount.base;

/**
 * Created by Administrator on 2018/1/3.
 */

public interface BaseView {

    void showProgress();

    void loadError();

    void loadFinish();

    /**
     * 处理服务器自定义的异常，需页面自己实现
     * @param code
     * @param msg
     */
    void loadMsgOrError(int code, String msg);

}
