package com.example.android_cjj118.base;

import androidx.multidex.MultiDexApplication;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

import java.net.MulticastSocket;

import cn.bmob.v3.Bmob;
public class MyApplication  extends MultiDexApplication {

    @Override
    public void onCreate() {

        super.onCreate();
        // bmob初始化 云端id
        Bmob.initialize(this, "1a996379a62bb405deecd590dd58507e");

        // 百度地图初始化
        SDKInitializer.setAgreePrivacy(getApplicationContext(),true);
        SDKInitializer.initialize(this);

        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        LocationClient.setAgreePrivacy(true);
    }
}
