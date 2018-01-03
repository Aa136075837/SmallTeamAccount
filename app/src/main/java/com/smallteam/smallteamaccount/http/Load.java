package com.smallteam.smallteamaccount.http;

import com.google.gson.GsonBuilder;
import com.smallteam.smallteamaccount.BuildConfig;
import com.smallteam.smallteamaccount.constant.SysConstant;
import com.smallteam.smallteamaccount.http.api.ApiService;
import com.smallteam.smallteamaccount.utils.L;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/1/3.
 */

public class Load {
    private volatile static Retrofit mRetrofit;

    /**
     * 创建网络请求<p> 使用 ApiService</p>
     */
    public static ApiService createApi() {
        if (mRetrofit == null) {
            synchronized (Load.class) {
                if (mRetrofit == null) {
                    createRetrofit();
                }
            }
        }
        return mRetrofit.create(ApiService.class);
    }

    /**
     * 创建网络请求 <p> 使用 自定义 Api 接口 </p>
     */
    public static <T> T createApi(Class<T> clazz) {
        if (mRetrofit == null) {
            synchronized (Load.class) {
                if (mRetrofit == null) {
                    createRetrofit();
                }
            }
        }
        return mRetrofit.create(clazz);
    }

    private static void createRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(SysConstant.CONFIG_NETWORK_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(SysConstant.CONFIG_NETWORK_TIME_OUT, TimeUnit.SECONDS)
            .sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts()).hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            }).addInterceptor(new HttpLoggingInterceptor(msg -> L.e("SmallTeam :: " + msg)).setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();
        Retrofit.Builder rfBuilder = new Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        rfBuilder.client(okHttpClient);
        mRetrofit = rfBuilder.build();
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return ssfFactory;
    }

}
