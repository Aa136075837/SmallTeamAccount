package com.smallteam.smallteamaccount.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.smallteam.smallteamaccount.base.SmallTeamApp;
import com.smallteam.smallteamaccount.bean.UserBean;

import org.json.JSONObject;

/**
 * 所有的关于sp的操作都通过该类实现
 * Created by TENGFEI on 2018/1/10.
 */
public class SpConfig {
    private static final SpConfig ourInstance = new SpConfig();

    public static SpConfig getInstance() {
        return ourInstance;
    }

    private SpConfig() {
    }

    public void setUser(UserBean value){
        Gson gson = new Gson();
        SPUtils.putString(SmallTeamApp.getInstance(),"USER",gson.toJson(value));
    }

    public UserBean getUser(){
        String user = SPUtils.getString(SmallTeamApp.getInstance(), "USER");
        if (TextUtils.isEmpty(user)){
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(user,UserBean.class);
    }
}
