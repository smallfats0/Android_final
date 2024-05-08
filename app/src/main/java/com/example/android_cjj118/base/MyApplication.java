package com.example.android_cjj118.base;

import androidx.multidex.MultiDexApplication;

import java.net.MulticastSocket;

import cn.bmob.v3.Bmob;
public class MyApplication  extends MultiDexApplication {

    @Override
    public void onCreate() {

        super.onCreate();
        // bmob初始化 云端id
        Bmob.initialize(this, "1a996379a62bb405deecd590dd58507e");
    }
}
