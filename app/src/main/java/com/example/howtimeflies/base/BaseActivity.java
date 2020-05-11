package com.example.howtimeflies.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.howtimeflies.util.ActivityCollector;
import com.example.howtimeflies.util.ToastUtils;
import com.xuexiang.xui.XUI;

/**
 * @author coderXshuai
 */
public class BaseActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //XUI
        XUI.initTheme(this);
        //进入动画设置
        getWindow().setEnterTransition(new Fade().setDuration(500));
        //退出动画设置
        getWindow().setExitTransition(new Fade().setDuration(500));
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    /**
     * Toast弹窗
     *
     * @param text
     */
    public void showMessage(String text) {
        ToastUtils.toast(text);
    }

    /**
     * 不带任何参数的activity跳转
     *
     * @param clz 要跳转的activity类名.class 例如：MainActivity.class
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * 携带数据的页面跳转
     *
     * @param clz    要跳转的activity类名
     * @param bundle bundle格式数据
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 带有请求返回的跳转
     *
     * @param cls         要跳转的activity类名
     * @param bundle      bundle格式数据
     * @param requestCode 请求码
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}
