package com.smallteam.smallteamaccount.http.api;

import com.smallteam.smallteamaccount.bean.GroupBean;
import com.smallteam.smallteamaccount.bean.NewAccountBean;
import com.smallteam.smallteamaccount.bean.NormalBean;
import com.smallteam.smallteamaccount.bean.ServerResultBean;
import com.smallteam.smallteamaccount.bean.UserBean;

import java.util.List;

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

    @POST("accountController/loginPhone")
    Observable<ServerResultBean<UserBean>> login(@Body RequestBody body);

    @POST("userGroup/getGroupByUserId")
    Observable<ServerResultBean<List<GroupBean>>> getGroups(@Body RequestBody body);

    @POST("userGroup/addOne")
    Observable<ServerResultBean<NewAccountBean>> addOneAccount(@Body RequestBody body);


}
