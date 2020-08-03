package com.example.howtimeflies.util;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * 全局常量类
 */
public class Constant {

    //格式如下：请求url
    public static String SERVER_URL = "";

    public static String[] titles = new String[]{
            "麻疹预防'",
            "水痘预防",
            "登革热病毒预防",
            "流行性感冒        ",
            "疫苗预防",
    };

    public static String[] urls = new String[]{//640*360 360/640=0.5625
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576254995485&di=152b5776121199504e48d57bb16f0312&imgtype=0&src=http%3A%2F%2Ffx120.120askimages.com%2F120ask_news%2F2016%2F0605%2F201606051465107753185898.jpg",//伪装者:胡歌演绎"痞子特工"
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576255028958&di=d9ba1766032db973ac3dc60e599af7f4&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fxian%2Fpics%2Fhv1%2F147%2F75%2F2297%2F149381697.jpg",//无心法师:生死离别!月牙遭虐杀
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1463013633,2261163220&fm=26&gp=0.jpg",//花千骨:尊上沦为花千骨
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=934540279,4028245722&fm=15&gp=0.jpg",//综艺饭:胖轩偷看夏天洗澡掀波澜
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=934540279,4028245722&fm=15&gp=0.jpg",//碟中谍4:阿汤哥高塔命悬一线,超越不可能
    };
    public static final String[] X_DATA_SET = new String[]{
            "一", "二", "三", "四", "五", "六", "日"
    };
    public static final ArrayList<Integer> BAR_COLORS = new ArrayList<>();
    public static final ArrayList<String> PIE_TYPES = new ArrayList<>();
    public static final ArrayList<Integer> PIE_COLORS = new ArrayList<>();

    static {
        BAR_COLORS.add(Color.parseColor("#81D2FF"));
        BAR_COLORS.add(Color.parseColor("#FCD2E7"));
        BAR_COLORS.add(Color.parseColor("#FBFF81"));
        BAR_COLORS.add(Color.parseColor("#81FF9B"));
        BAR_COLORS.add(Color.parseColor("#EF81FF"));
        BAR_COLORS.add(Color.parseColor("#007CFF"));
        BAR_COLORS.add(Color.parseColor("#FFB681"));
    }

    static {
        PIE_TYPES.add("学习类");
        PIE_TYPES.add("影音类");
        PIE_TYPES.add("社交类");
        PIE_TYPES.add("资讯类");
        PIE_TYPES.add("其它类");
    }

    static {
        PIE_COLORS.add(Color.parseColor("#ED7D31"));
        PIE_COLORS.add(Color.parseColor("#5B9BD5"));
        PIE_COLORS.add(Color.parseColor("#FFC000"));
        PIE_COLORS.add(Color.parseColor("#A5A5A5"));
        PIE_COLORS.add(Color.parseColor("#FFB681"));
    }
}
