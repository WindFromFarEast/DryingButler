package com.studio.dryingbutler.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.studio.dryingbutler.application.MyApplication;


/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.Utils
 * file name: SharedUtil
 * creator: WindFromFarEast
 * created time: 2017/10/22 18:48
 * description: TODO
 */

public class SharedUtil
{
    public static SharedPreferences preferences=MyApplication.getContext().getSharedPreferences("data",Context.MODE_PRIVATE);

    public static void saveStringData(String key,String value)
    {
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static String getStringData(String key)
    {
        return preferences.getString(key,"NoData");
    }
}
