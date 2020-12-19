package com.example.howtimeflies.entity;

import android.app.usage.UsageStats;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import lombok.Data;

@Data
public class AppInfo implements Parcelable, Comparable<AppInfo> {
    private String packageName;
    private ApplicationInfo appInfo;
    private UsageStats usageStats;
    public static final Creator<AppInfo> CREATOR = new Creator<AppInfo>() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public AppInfo createFromParcel(Parcel source) {
            return new AppInfo(source);
        }

        @Override
        public AppInfo[] newArray(int size) {
            return new AppInfo[size];
        }
    };
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

    private Bitmap appIcon;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected AppInfo(Parcel in) {
        this.packageName = in.readString();
        this.appInfo = in.readParcelable(ApplicationInfo.class.getClassLoader());
        this.usageStats = in.readParcelable(UsageStats.class.getClassLoader());
        this.appIcon = in.readParcelable(Bitmap.class.getClassLoader());
        this.appName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.packageName);
        dest.writeParcelable(this.appInfo, flags);
        dest.writeParcelable(this.usageStats, flags);
        dest.writeParcelable(this.appIcon, flags);
        dest.writeString(this.appName);
    }
}
