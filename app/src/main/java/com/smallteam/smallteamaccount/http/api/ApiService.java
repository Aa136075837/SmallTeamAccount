package com.smallteam.smallteamaccount.http.api;

import com.smallteam.smallteamaccount.bean.NormalBean;
import com.smallteam.smallteamaccount.bean.ServerResultBean;
import com.smallteam.smallteamaccount.bean.UserBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/1/3.
 */

public interface ApiService {

    @POST("mock/5a41adb2b80e3f3080db1dd6/app")
    Observable<NormalBean> test(@Body RequestBody body);

    @POST("userController/loginPhone")
    Observable<NormalBean> niaBieLV(@Body RequestBody body);

    @POST("accountController/loginPhone")
    Observable<ServerResultBean<UserBean>> login(@Body RequestBody body);
}
