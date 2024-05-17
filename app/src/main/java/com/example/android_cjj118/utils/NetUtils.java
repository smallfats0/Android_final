package com.example.android_cjj118.utils;

import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory;
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtils {

    // IP地址
//    http://127.0.0.1:8080/topline/ 电脑 ，手机用10.0.2.2
//public static final String BASE_URL = "http://10.0.2.2:8080/topline/";
  public static final String BASE_URL = "http://192.168.189.1:8080/topline/";

    public static GetRequest get(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(GetRequest.class);
    }


}
