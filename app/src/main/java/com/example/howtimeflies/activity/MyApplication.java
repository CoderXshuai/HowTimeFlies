package com.example.howtimeflies.activity;

import android.app.Application;

import com.xuexiang.xui.XUI;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this);
        XUI.debug(true);
//        ActivityLifecycleCallbacks
//        ActivityLifeCycle lifecycleCallbacks = new ActivityLifeCycle();
//        registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }
}
