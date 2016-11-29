package com.example.mvpdemo.model.net;

import com.example.mvpdemo.utils.Config;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fmw on 2016/6/7.
 */
public class NetClient {

    public static final String HOST_URL = Config.HOST_URL;
    private static NetClient netClient;
    private static ApiService api;

    public NetClient(String hostUrl){
        //测试环境输出log
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(Config.D?HttpLoggingInterceptor.Level.BODY:HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(hostUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        api = retrofit.create(ApiService.class);
    }

    public static ApiService getApi(){
        netClient = new NetClient(HOST_URL);
        return netClient.api;
    }

}
