package com.example.mvpdemo.model.net;

import com.example.mvpdemo.model.entity.Picture;
import com.example.mvpdemo.model.net.resp.RespBody;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by fmw on 2016/6/7.
 */
public interface ApiService {

    //获取图片
    @GET("listjson")
//    Call<RespBody<Picture>> getPictures(@QueryMap HashMap<String,String> querys);
    Observable<RespBody<Picture>> getPictures(@QueryMap HashMap<String,String> querys);




}
