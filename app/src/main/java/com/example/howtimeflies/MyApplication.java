package com.example.howtimeflies;

import android.app.Application;

import com.xuexiang.xui.XUI;

/**
 * @author coderXshuai
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this);
        XUI.debug(true);
    }
}
