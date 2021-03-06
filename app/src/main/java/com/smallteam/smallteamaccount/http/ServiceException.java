package com.smallteam.smallteamaccount.http;

/**
 * Created by Administrator on 2018/1/3.
 */

public class ServiceException extends IllegalAccessException {



    private int code;
    private String msg;

    public static ServiceException create(int code, String msg){
        return new ServiceException(code, msg);
    }

    private ServiceException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
