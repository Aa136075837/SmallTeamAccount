package com.smallteam.smallteamaccount.base;

import android.app.Application;
import android.os.Handler;

import com.mob.MobSDK;

/**
 * Created by Administrator on 2018/1/3.
 */

public class SmallTeamApp extends Application {
    private static SmallTeamApp mInstance;
    private static Handler mMainThreadHandler;

    public static SmallTeamApp getInstance() {
        return mInstance;
    }
    public static Handler getHandler(){
        return mMainThreadHandler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mMainThreadHandler = new Handler();
        MobSDK.init(this);
    }

}
