package com.studio.dryingbutler.Utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.Utils
 * file name: PermissionUtil
 * creator: WindFromFarEast
 * created time: 2017/9/1 14:10
 * description: 运行时权限工具类
 */

public class PermissionUtil
{
    //用来存储未申请过的权限
    private static List<String> mList = new ArrayList<>();
    //权限请求码
    public static final int LOCATION_CODE=1;
    //同时申请多个权限,返回未申请过权限的数量
    public static int requestPermission(Activity activity, int requestCode, String... permission)
    {
        //清空List
        mList.clear();
        //获取申请权限的数量
        int permissionCount = permission.length;
        //判断是否有申请权限
        if (permissionCount > 0)
        {
            //申请每个权限
            for (int i = 0; i < permissionCount; i++)
            {
                if (ContextCompat.checkSelfPermission(activity, permission[i]) != PackageManager.PERMISSION_GRANTED)
                {
                    //将没有申请过的权限保存起来
                    mList.add(permission[i]);
                }
            }
            //将没申请过的权限由List转为数组后一次性申请
            permission = mList.toArray(new String[mList.size()]);
            if (permission.length != 0)
            {
                ActivityCompat.requestPermissions(activity, permission, requestCode);
            }
        }
        else
        {
            Toast.makeText(activity, "请添加要申请的权限!", Toast.LENGTH_SHORT).show();
        }
        return mList.size();
    }
}
