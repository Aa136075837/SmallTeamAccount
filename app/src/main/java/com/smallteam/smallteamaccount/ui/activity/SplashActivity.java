package com.smallteam.smallteamaccount.ui.activity;

import android.os.Bundle;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.base.BaseActivity;
import com.smallteam.smallteamaccount.base.SmallTeamApp;
import com.smallteam.smallteamaccount.utils.SpConfig;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SmallTeamApp.getHandler().postDelayed(() -> {
            if (SpConfig.getInstance().getUser() != null) {
                startAcvitity(MainActivity.class);
            }else {
                startAcvitity(LoginActivity.class);
            }
            finish();
        },3*1000);
    }
}
