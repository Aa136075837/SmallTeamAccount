package com.smallteam.smallteamaccount.base;

import android.app.Application;

import com.mob.MobSDK;

/**
 * Created by Administrator on 2018/1/3.
 */

public class SmallTeamApp extends Application {
    private static SmallTeamApp mInstance;

    public static SmallTeamApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        MobSDK.init(this);
    }

}
