package com.example.howtimeflies.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.howtimeflies.entity.AppInfo;
import com.example.howtimeflies.task.LoadAppUsageTask;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AppInfoList {
    private static List<AppInfo> mItems;
    private long maxTime;// 当前列表中 使用最久的APP时间 用于计算进度条百分比
    private long beginTime = CommonUtil.getTodayTime0();
    private long endTime = System.currentTimeMillis();

    public AppInfoList(long beginTime, long endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public AppInfoList() {
        this.beginTime = CommonUtil.getTodayTime0();
        this.endTime = System.currentTimeMillis();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public List<AppInfo> getmItems() throws ExecutionException, InterruptedException {
        LoadAppUsageTask mLoadAppUsageTask = new LoadAppUsageTask(beginTime, endTime, list -> {
            mItems = list;
        });
        mItems = mLoadAppUsageTask.execute().get();
        Collections.sort(mItems);
        return mItems;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initAdapter() {
        if (JListKit.isNotEmpty(mItems)) {
            Collections.sort(mItems);// 按使用时长排序
            maxTime = mItems.get(0).getUsageStats().getTotalTimeInForeground();
        } else {
            maxTime = 1;
        }
    }

    public AppInfo findPackageNameByAppName(String appName) {
        for (AppInfo appInfo : mItems) {
            if (appInfo.getAppName().equals(appName)) {
                return appInfo;
            }
        }
        return null;
    }
}
