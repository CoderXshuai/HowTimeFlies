package com.example.howtimeflies.entity;

import android.app.usage.UsageStats;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;

import lombok.Data;

@Data
public class AppInfo {
    private String packageName;
    private ApplicationInfo appInfo;
    private UsageStats usageStats;
    private Drawable appIcon;
    private String appName;
}
