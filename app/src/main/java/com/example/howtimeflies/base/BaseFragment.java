package com.example.howtimeflies.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseFragment 封装类
 * 所有的碎片继承此类
 */
public abstract class BaseFragment extends Fragment {
    public static final String FRAGMENT_NAME = "fragment_name";
    public String fragmentName;
    private View mContentView;
    private Context mContext;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(setLayoutResourceID(), container, false);
        unbinder = ButterKnife.bind(this, mContentView);
        mContext = getContext();
        init();
        setUpData();
        setUpView();
        return mContentView;
    }

    /**
     * 此方法用于返回Fragment设置ContentView的布局文件资源ID * *
     *
     * @return 布局文件资源ID
     */
    protected abstract int setLayoutResourceID();

    /**
     * 一些View的相关操作
     */
    protected abstract void setUpView();

    /**
     * 一些Data的相关操作
     */
    protected abstract void setUpData();

    /**
     * 此方法用于初始化成员变量及获取Intent传递过来的数据
     * 注意：这个方法中不能调用所有的View，因为View还没有被初始化，
     * 要使用View在initView方法中调用
     */
    protected void init() {
        if (getArguments() != null) {
            fragmentName = getArguments().getString(FRAGMENT_NAME);
        }
    }

    public View getContentView() {
        return mContentView;
    }

    public Context getMContext() {
        return mContext;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //  unbinder.unbind();
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
        intent.setClass(mContext, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}