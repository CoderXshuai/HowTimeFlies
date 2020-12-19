package com.example.howtimeflies.entity;

import android.app.usage.UsageStats;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;

import lombok.Data;

@Data
public class AppInfo implements Serializable, Comparable<AppInfo> {
    private String packageName;
    private ApplicationInfo appInfo;
    private UsageStats usageStats;
    private Drawable appIcon;
    private String appName;

    public AppInfo(String packageName, UsageStats usageStats) {
        this.packageName = packageName;
        this.usageStats = usageStats;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int compareTo(AppInfo o) {
        try {
            long l = o.getUsageStats().getTotalTimeInForeground() - getUsageStats().getTotalTimeInForeground();
            l /= 1000;
            if (l >= Integer.MAX_VALUE)
                l = Integer.MAX_VALUE - 1;
            return (int) (l);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
