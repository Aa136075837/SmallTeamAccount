package com.smallteam.smallteamaccount.utils;

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

    public void setUser(){

    }
}
