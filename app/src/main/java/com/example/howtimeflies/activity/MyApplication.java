package com.example.howtimeflies.activity;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.xuexiang.xui.XUI;

import cn.leancloud.AVOSCloud;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this);
        XUI.debug(true);
        //LeanCloud
        AVOSCloud.initialize(this,"BzP5arkldufbLqrmkSpTK3zC-gzGzoHsz","DALYLX1WgdC9z1wfl8Konc1c","https://bzp5arkl.lc-cn-n1-shared.com");

//        设置增加方法数
        MultiDex.install(this);
    }
}
