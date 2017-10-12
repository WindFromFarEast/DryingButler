package com.studio.dryingbutler.application;

import android.app.Application;
import android.content.Context;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.application
 * file name: MyApplication
 * creator: WindFromFarEast
 * created time: 2017/9/1 13:40
 * description: Application
 */

public class MyApplication extends Application
{
    private static Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context=getApplicationContext();
    }

    //对外提供Context的接口
    public static Context getContext()
    {
        return context;
    }
}
