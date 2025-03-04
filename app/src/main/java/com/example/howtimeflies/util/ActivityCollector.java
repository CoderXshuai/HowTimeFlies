package com.example.howtimeflies.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity管理工具类
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity  activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity  activity){
        activities.remove(activity);
    }

    public static void finishall(){
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
