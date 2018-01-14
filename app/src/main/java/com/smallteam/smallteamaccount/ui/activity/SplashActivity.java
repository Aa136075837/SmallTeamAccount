package com.smallteam.smallteamaccount.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.base.BaseActivity;
import com.smallteam.smallteamaccount.base.SmallTeamApp;
import com.smallteam.smallteamaccount.utils.SpConfig;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SmallTeamApp.getHandler().postDelayed(() -> {
            if (SpConfig.getInstance().getUser() != null) {
                startAcvitity(MainActivity.class);
            }else {
                startAcvitity(LoginActivity.class);
            }
        },3*1000);
    }
}
