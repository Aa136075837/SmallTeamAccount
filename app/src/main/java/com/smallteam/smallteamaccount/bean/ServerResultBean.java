package com.smallteam.smallteamaccount.bean;

import java.io.Serializable;

/**
 *  服务器返回的数据格式
 * Created by TENGFEI on 2018/1/9.
 */
public class ServerResultBean<T> implements Serializable {
    private int code;
    private String msg;
    private T bean;

    public int getCode () {
        return code;
    }

    public void setCode (int code) {
        this.code = code;
    }

    public String getMsg () {
        return msg;
    }

    public void setMsg (String msg) {
        this.msg = msg;
    }

    public T getBean () {
        return bean;
    }

    public void setBean (T bean) {
        this.bean = bean;
    }
}
