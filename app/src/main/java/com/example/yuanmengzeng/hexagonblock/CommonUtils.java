package com.example.yuanmengzeng.hexagonblock;

import java.util.Random;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yuanmengzeng on 2016/4/21.
 */
public class CommonUtils
{

    private final static String PREFS_FILE_NAME = "HEXAGON_BLOCK";

    /**
     * @return 获取系统状态栏的高度
     */
    public static int getStatusHeight(Context mContext)
    {
        int statusHeight = 0;
        int resouceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resouceId > 0)
            statusHeight = mContext.getResources().getDimensionPixelSize(resouceId);
        return statusHeight;
    }

    public static int readPrefsInt(Context context, String key)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_APPEND);
        if (sharedPreferences == null)
            return 0;
        else
            return sharedPreferences.getInt(key, 0);
    }

    public static void writePrefsInt(Context context, String key, int value)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_APPEND).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static String readPrefsString(Context context, String key)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_APPEND);
        if (sharedPreferences == null)
            return null;
        else
            return sharedPreferences.getString(key, "");
    }

    public static void writePrefsString(Context context, String key, String value)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_APPEND).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static int getHeapBg()
    {
        int count = CommonData.HEAP_BG.length;
        Random random = new Random();
        int order = random.nextInt(count);
        return CommonData.HEAP_BG[order];
    }

}
