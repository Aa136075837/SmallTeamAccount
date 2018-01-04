package com.smallteam.smallteamaccount.http.api;

import com.smallteam.smallteamaccount.bean.NormalBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/1/3.
 */

public interface ApiService {

    @POST("mock/5a41adb2b80e3f3080db1dd6/app/login/login")
    Observable<NormalBean> test(@Body RequestBody body);

    @POST("userController/verifyCode")
    Observable<NormalBean> niaBieLV(@Body RequestBody body);
}
