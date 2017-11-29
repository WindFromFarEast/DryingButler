package com.studio.dryingbutler.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gizwits.gizwifisdk.api.GizWifiSDK;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.gizwits.gizwifisdk.listener.GizWifiSDKListener;
import com.studio.dryingbutler.R;
import com.studio.dryingbutler.Utils.PermissionUtil;
import com.studio.dryingbutler.Utils.SharedUtil;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import static android.app.Activity.RESULT_OK;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: Equipment
 * creator: WindFromFarEast
 * created time: 2017/9/5 17:21
 * description: 设备页面
 */

public class Equipment extends Fragment
{
    public static int CAMERA_PERMISSION_CODE=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_equipment,null);
        PermissionUtil.requestPermission(getActivity(),CAMERA_PERMISSION_CODE, Manifest.permission.CAMERA);//请求摄像头的运行时权限
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case 1:
            {
                if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    //用户同意了权限
                }
                else
                {
                    //用户没有同意权限
                    Toast.makeText(getActivity(),"拒绝此权限将无法使用该App",Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
                break;
            }
        }
    }
}
