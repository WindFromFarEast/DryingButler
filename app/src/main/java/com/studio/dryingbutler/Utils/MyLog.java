package com.studio.dryingbutler.Utils;

import android.util.Log;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.Utils
 * file name: MyLog
 * creator: WindFromFarEast
 * created time: 2017/9/23 14:41
 * description: TODO
 */

public class MyLog
{
    private static int key=1;

    public static void printInfo(String info)
    {
        if (key==1)
        {
            Log.i("DryingButler", info);
        }
    }
}
