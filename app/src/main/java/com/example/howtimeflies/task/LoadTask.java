package com.example.howtimeflies.task;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.example.howtimeflies.MyApplication;
import com.example.howtimeflies.entity.AppInfo;
import com.example.howtimeflies.util.JDateKit;
import com.example.howtimeflies.util.JListKit;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class LoadTask extends AsyncTask<String, Void, AppInfo> {
    private static final String TAG = "LoadTask";
    private LoadTask.Callback mCallback;
    private long beginTime, endTime;
    private PackageManager mPackageManager;


    public LoadTask(long beginTime, long endTime, LoadTask.Callback mCallback) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.mCallback = mCallback;
        mPackageManager = MyApplication.getApplication().getPackageManager();
    }

    // 方法1：onPreExecute（）
    // 作用：执行 线程任务前的操作
    @Override
    protected void onPreExecute() {
        // 执行前显示提示
        Log.d(TAG, "********** 开始获取应用使用情况 **********");
    }

    // 方法2：doInBackground（）
    // 作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
    // 此处通过计算从而模拟“加载进度”的情况
    @Override
    protected AppInfo doInBackground(String... strings) {
        return readAppUser(strings[0]);
    }

    private ArrayList<AppInfo> readAppUsageList() {
        ArrayList<AppInfo> mItems = JListKit.newArrayList();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            UsageStatsManager usage = (UsageStatsManager) MyApplication.getApplication().getSystemService(Context.USAGE_STATS_SERVICE);
            if (usage == null) return mItems;
            // 查询并按包名进行聚合操作
            Map<String, UsageStats> statsMap = usage.queryAndAggregateUsageStats(beginTime, endTime);
            Set<String> keySet = statsMap.keySet();
            for (String packageName : keySet) {
                UsageStats usageStats = statsMap.get(packageName);
                if (usageStats == null) continue;
                long totalTimeInForeground = usageStats.getTotalTimeInForeground();
                if (totalTimeInForeground <= 0) continue;// 小于1秒的都按照没有打开过处理

                AppInfo AppInfo = new AppInfo(packageName, usageStats);
                ApplicationInfo info = getAppInfo(packageName);
                AppInfo.setAppInfo(info);
                if (info != null) {
                    // 获取应用名称
                    String label = (String) info.loadLabel(mPackageManager);
                    Drawable icon = info.loadIcon(mPackageManager);
                    AppInfo.setAppName(label);
                    AppInfo.setAppIcon(icon);
                } else {
                    AppInfo.setAppName("应用已卸载");
                    // Log.e(TAG, "已经找不到包名为[" + packageName + "]的应用");
                }

                mItems.add(AppInfo);
                // 打印日志
                if (Boolean.parseBoolean("true")) {
                    Log.d("UsageStats", "**********************************************");
                    Log.d("UsageStats", packageName);
                    // Log.d("UsageStats", "运行时长:" + JDateKit.timeToStringChineChinese(totalTimeInForeground));
                    Log.d("UsageStats", String.format("运行时长:%s (%sms)", JDateKit.timeToStringChineChinese(totalTimeInForeground), totalTimeInForeground));
                    String fmt = "yyyy-MM-dd HH:mm:ss.SSS";
                    Log.d("UsageStats", "开始启动:" + JDateKit.timeToDate(fmt, usageStats.getFirstTimeStamp()));
                    Log.d("UsageStats", "最后启动:" + JDateKit.timeToDate(fmt, usageStats.getLastTimeStamp()));
                    Log.d("UsageStats", "最近使用:" + JDateKit.timeToDate(fmt, usageStats.getLastTimeUsed()));
                }
            }
        }
        return mItems;
    }

    private AppInfo readAppUser(String packageName) {
        AppInfo appInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            UsageStatsManager usage = (UsageStatsManager) MyApplication.getApplication().getSystemService(Context.USAGE_STATS_SERVICE);
            // 查询并按包名进行聚合操作
            Map<String, UsageStats> statsMap = usage.queryAndAggregateUsageStats(beginTime, endTime);
            UsageStats usageStats = statsMap.get(packageName);
            if (usageStats == null)
                return null;
            long totalTimeInForeground = usageStats.getTotalTimeInForeground();
            if (totalTimeInForeground <= 0)
                // 小于1秒的都按照没有打开过处理
                return null;
            appInfo = new AppInfo(packageName, usageStats);
            ApplicationInfo info = getAppInfo(packageName);
            appInfo.setAppInfo(info);
            if (info != null) {
                // 获取应用名称
                String label = (String) info.loadLabel(mPackageManager);
                Drawable icon = info.loadIcon(mPackageManager);
                appInfo.setAppName(label);
                appInfo.setAppIcon(icon);
            } else {
                appInfo.setAppName("应用已卸载");
                // Log.e(TAG, "已经找不到包名为[" + packageName + "]的应用");
            }
            // 打印日志
            if (Boolean.parseBoolean("true")) {
                Log.d("UsageStats", "**********************************************");
                Log.d("UsageStats", packageName);
                // Log.d("UsageStats", "运行时长:" + JDateKit.timeToStringChineChinese(totalTimeInForeground));
                Log.d("UsageStats", String.format("运行时长:%s (%sms)", JDateKit.timeToStringChineChinese(totalTimeInForeground), totalTimeInForeground));
                String fmt = "yyyy-MM-dd HH:mm:ss.SSS";
                Log.d("UsageStats", "开始启动:" + JDateKit.timeToDate(fmt, usageStats.getFirstTimeStamp()));
                Log.d("UsageStats", "最后启动:" + JDateKit.timeToDate(fmt, usageStats.getLastTimeStamp()));
                Log.d("UsageStats", "最近使用:" + JDateKit.timeToDate(fmt, usageStats.getLastTimeUsed()));
            }

        }
        return appInfo;
    }

    private ApplicationInfo getAppInfo(String pkgName) {
        try {
            // ApplicationInfo info = mPackageManager.getApplicationInfo(pkgName, PackageManager.GET_ACTIVITIES);
            return mPackageManager.getApplicationInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            // e.printStackTrace();
            Log.e(TAG, "已经找不到包名为[" + pkgName + "]的应用");
        }
        return null;
    }

    // 方法3：onPostExecute（）
    // 作用：接收线程任务执行结果、将执行结果显示到UI组件
    @Override
    protected void onPostExecute(AppInfo appInfo) {
        super.onPostExecute(appInfo);
        if (mCallback != null)
            mCallback.onPostExecute(appInfo);
    }

    public interface Callback {
        void onPostExecute(AppInfo appInfo);
    }


}
