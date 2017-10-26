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

public class Equipment extends Fragment implements View.OnClickListener
{
    private Button btn_equipment_empty_add;
    private Button btn_equipment_cancel_add;
    private RelativeLayout rl_equipment_empty;
    private RelativeLayout rl_equipment_add;
    private EditText et_equipment_number;
    private EditText et_equipment_code;
    private EditText et_equipment_alias;
    public static int CAMERA_PERMISSION_CODE=1;
    private GizWifiSDKListener gizListener=new GizWifiSDKListener()
    {
        @Override
        public void didBindDevice(GizWifiErrorCode result, String did)
        {
            if (result==GizWifiErrorCode.GIZ_SDK_SUCCESS)
            {
                Toast.makeText(getActivity(),"绑定成功",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getActivity(),"绑定失败",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_equipment,null);
        initView(view);
        PermissionUtil.requestPermission(getActivity(),CAMERA_PERMISSION_CODE, Manifest.permission.CAMERA);//请求摄像头的运行时权限
        return view;
    }

    private void initView(View view)
    {
        btn_equipment_cancel_add= (Button) view.findViewById(R.id.btn_equipment_cancel_add);
        btn_equipment_empty_add= (Button) view.findViewById(R.id.btn_equipment_empty_add);
        rl_equipment_empty= (RelativeLayout) view.findViewById(R.id.rl_equipment_empty);
        rl_equipment_add= (RelativeLayout) view.findViewById(R.id.rl_equipment_add);
        et_equipment_alias= (EditText) view.findViewById(R.id.et_equipment_alias);
        et_equipment_number= (EditText) view.findViewById(R.id.et_equipment_number);
        et_equipment_code= (EditText) view.findViewById(R.id.et_equipment_code);

        btn_equipment_empty_add.setOnClickListener(this);
        btn_equipment_cancel_add.setOnClickListener(this);

        GizWifiSDK.sharedInstance().setListener(gizListener);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_equipment_empty_add:
            {
                //rl_equipment_empty.setVisibility(View.GONE);
                //rl_equipment_add.setVisibility(View.VISIBLE);
                Intent openCameraIntent = new Intent(getActivity(),CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
                break;
            }
            case R.id.btn_equipment_cancel_add:
            {
                //rl_equipment_add.setVisibility(View.GONE);
                //rl_equipment_empty.setVisibility(View.VISIBLE);
                break;
            }
        }
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK)
        {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            GizWifiSDK.sharedInstance().bindDeviceByQRCode(SharedUtil.getStringData("uid"),
                    SharedUtil.getStringData("token"),scanResult);
        }
    }
}
