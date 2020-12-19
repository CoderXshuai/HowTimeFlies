package com.example.howtimeflies;

import android.app.Application;
import android.content.Context;

import com.xuexiang.xui.XUI;

public class MyApplication extends Application {
    private static MyApplication mApplication;

    /**
     * 返回上下文
     *
     * @return
     */
    public static Context getApplication() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this);
        XUI.debug(true);
        mApplication = this;
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        mApplication = null;
    }
}
