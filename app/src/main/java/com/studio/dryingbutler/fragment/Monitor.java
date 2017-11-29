package com.studio.dryingbutler.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.api.GizWifiSDK;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.gizwits.gizwifisdk.listener.GizWifiDeviceListener;
import com.gizwits.gizwifisdk.listener.GizWifiSDKListener;
import com.studio.dryingbutler.R;
import com.studio.dryingbutler.Utils.MyLog;
import com.studio.dryingbutler.Utils.PermissionUtil;
import com.studio.dryingbutler.Utils.SharedUtil;
import com.studio.dryingbutler.adapter.MonitorDeviceAdapter;
import com.studio.dryingbutler.entity.Device;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static android.app.Activity.RESULT_OK;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: Monitor
 * creator: WindFromFarEast
 * created time: 2017/9/5 17:19
 * description:
 */

public class Monitor extends Fragment
{
    private ListView lv_monitor_device;
    private List<Device> mList=new ArrayList<>();
    private MonitorDeviceAdapter adapter;
    private Button btn_equipment_empty_add_temp;
    private Button btn_equipment_show_list;
    public static int CAMERA_PERMISSION_CODE=1;
    private List<GizWifiDevice> devices = GizWifiSDK.sharedInstance().getDeviceList();
    private ConcurrentHashMap<String, Object> command = new ConcurrentHashMap<String, Object> ();
    private GizWifiDevice gizWifiDevice;
    private int flag=0;
    private int temp_flag=0;
    private Button btn_warning_motor_no;
    private Button btn_warning_motor_yes;
    private Button btn_warning_temp_no;
    private Button btn_warning_temp_yes;
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

        //获取绑定设备列表回调
        @Override
        public void didDiscovered(GizWifiErrorCode result, List<GizWifiDevice> deviceList)
        {
            if (result!=GizWifiErrorCode.GIZ_SDK_SUCCESS)
            {
                MyLog.printInfo("result:"+result.name());
                //Toast.makeText(getActivity(),"发现列表失败",Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(getActivity(),"发现列表成功",Toast.LENGTH_SHORT).show();
            MyLog.printInfo(deviceList.toString());
            //Toast.makeText(getActivity(),"设备列表："+deviceList.toString(),Toast.LENGTH_SHORT).show();
            devices=deviceList;
            if (devices.size()>0)
            {
                for (int i=1;i<=devices.size();i++)
                {
                    //
                    if (mList.size()==devices.size())
                    {
                        break;
                    }
                    addToList();
                    setListView();
                }
            }
            //devices.get(0).getDeviceStatus(null);
        }
    };

    private GizWifiDeviceListener deviceListener=new GizWifiDeviceListener()
    {
        @Override
        public void didSetSubscribe(GizWifiErrorCode result, GizWifiDevice device, boolean isSubscribed)
        {
            if (result==GizWifiErrorCode.GIZ_SDK_SUCCESS)
            {
                //Toast.makeText(getActivity(),"订阅成功",Toast.LENGTH_SHORT).show();
            }
            else
            {
                //Toast.makeText(getActivity(),"订阅失败",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void didReceiveData(GizWifiErrorCode result, GizWifiDevice device, ConcurrentHashMap<String, Object> dataMap, int sn)
        {
            if (result==GizWifiErrorCode.GIZ_SDK_SUCCESS)
            {
                /**
                if (dataMap.get("data")!=null)
                {
                    ConcurrentHashMap<String, Object> map = (ConcurrentHashMap<String, Object>) dataMap.get("data");
                    if ((boolean)map.get("Infrared"))
                    {

                    }
                }
                 **/
                mList.get(0).setWork(true);
                if (sn==1)
                {
                    //Toast.makeText(getActivity(),"改变电机转速中",Toast.LENGTH_SHORT).show();
                }
                if (sn==5)
                {
                    //Toast.makeText(getActivity(),"改变LED中",Toast.LENGTH_SHORT).show();
                }
                else if (dataMap.get("data")!=null)
                {
                    ConcurrentHashMap<String, Object> map = (ConcurrentHashMap<String, Object>) dataMap.get("data");
//                    Toast.makeText(getActivity(),"Temperature:"+map.get("Temperature").toString()+"\n"
//                            +"Humidity:"+map.get("Humidity"),Toast.LENGTH_SHORT).show();
                    //将温湿度缓存到本地以便在另外的Activity获取
                    SharedUtil.saveStringData("Temperature",map.get("Temperature").toString());
                    SharedUtil.saveStringData("Humidity",map.get("Humidity").toString());
                    //Toast.makeText(getActivity(),"红外线状态:"+map.get("Infrared").toString(),Toast.LENGTH_SHORT).show();
                    if ((boolean)(map.get("Infrared"))&&(flag==0))
                    {
                        //Toast.makeText(getActivity(),"警告：电机出现异常，请检查",Toast.LENGTH_SHORT).show();
                        //弹出警告
                        openWindow();
                        flag=1;//标志到出错位
                    }
                    else if (!((boolean)(map.get("Infrared"))))
                    {
                        //flag=0;//标志到正常位
                    }
                    if ((Integer.parseInt(SharedUtil.getStringData("Temperature"))>=35)&&(temp_flag==0))
                    {
                        //Toast.makeText(getActivity(),"警告：温度过高，请检查",Toast.LENGTH_SHORT).show();
                        openTempWindow();
                        temp_flag=1;
                    }
                    else if (Integer.parseInt(SharedUtil.getStringData("Temperature"))<35)
                    {
                        temp_flag=0;
                    }
                }
                else
                {
                    Toast.makeText(getActivity(),"没有获取到温度值",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(getActivity(),"获取设备信息出错！",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_monitor,null);
        initView(view);
        PermissionUtil.requestPermission(getActivity(),CAMERA_PERMISSION_CODE, Manifest.permission.CAMERA);//请求摄像头的运行时权限
        //addToList();
        //setListView();
        return view;
    }

    private void initView(View view)
    {
        lv_monitor_device= (ListView) view.findViewById(R.id.lv_monitor_device);
        btn_equipment_empty_add_temp= (Button) view.findViewById(R.id.btn_equipment_empty_add_temp);
        btn_equipment_show_list= (Button) view.findViewById(R.id.btn_equipment_show_list);

        btn_equipment_empty_add_temp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent openCameraIntent = new Intent(getActivity(),CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        btn_equipment_show_list.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //回调获取绑定设备列表
                GizWifiSDK.sharedInstance().getBoundDevices(SharedUtil.getStringData("uid"),SharedUtil.getStringData("token"));
                //
                if (devices.size()>0)
                {
                    devices.get(0).getDeviceStatus(null);
                }
                else
                {
                    Toast.makeText(getActivity(),"服务器不稳定,请重试",Toast.LENGTH_SHORT).show();
                }
            }
        });
        GizWifiSDK.sharedInstance().setListener(gizListener);
    }

    //填充设备列表数据源
    private void addToList()
    {
        gizWifiDevice=devices.get(0);
        gizWifiDevice.setSubscribe(true);//订阅设备
        gizWifiDevice.setListener(deviceListener);
        Device device1=new Device();
        device1.setName("A");
        device1.setWork(true);
        device1.setFanWork(true);
        device1.setBatteryWork(true);
        device1.setCirFanOn(true);
        device1.setBlowerOn(true);
        device1.setAxuFanOn(true);
        device1.setFire(true);
        device1.setCoolOneOn(true);
        device1.setCoolTwoOn(true);
        device1.setValveOneOn(true);
        device1.setValveTwoOn(true);
        device1.setProcess(20);
        mList.add(device1);
        //MyLog.printInfo("列表长度:"+mList.size());
    }

    private void setListView()
    {
        adapter = new MonitorDeviceAdapter(mList, getActivity());
        lv_monitor_device.setAdapter(adapter);
        lv_monitor_device.setDivider(null);
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

    private void openWindow()
    {
        final WindowManager windowManager= (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params=new WindowManager.LayoutParams();
        params.width=WindowManager.LayoutParams.MATCH_PARENT;
        params.height=WindowManager.LayoutParams.MATCH_PARENT;
        params.flags=WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        params.format= PixelFormat.TRANSLUCENT;
        params.type=WindowManager.LayoutParams.TYPE_PHONE;
        final View windowView=LayoutInflater.from(getActivity()).inflate(R.layout.motor_warning_diaglog,null);
        btn_warning_motor_no= (Button) windowView.findViewById(R.id.btn_warning_motor_no);
        btn_warning_motor_yes= (Button) windowView.findViewById(R.id.btn_warning_motor_yes);
        btn_warning_motor_no.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                windowManager.removeView(windowView);
                flag=0;
            }
        });
        btn_warning_motor_yes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                windowManager.removeView(windowView);
                command.put("Motor_Speed",0);
                gizWifiDevice.write(command,12);
                flag=0;
            }
        });
        windowManager.addView(windowView,params);
    }

    private void openTempWindow()
    {
        final WindowManager windowManager= (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params=new WindowManager.LayoutParams();
        params.width=WindowManager.LayoutParams.MATCH_PARENT;
        params.height=WindowManager.LayoutParams.MATCH_PARENT;
        params.flags=WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        params.format= PixelFormat.TRANSLUCENT;
        params.type=WindowManager.LayoutParams.TYPE_PHONE;
        final View windowView=LayoutInflater.from(getActivity()).inflate(R.layout.temp_warning_dialog,null);
        btn_warning_temp_no= (Button) windowView.findViewById(R.id.btn_warning_temp_no);
        btn_warning_temp_yes= (Button) windowView.findViewById(R.id.btn_warning_temp_yes);
        btn_warning_temp_no.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                windowManager.removeView(windowView);
            }
        });
        btn_warning_temp_yes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                windowManager.removeView(windowView);
            }
        });
        windowManager.addView(windowView,params);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (devices.size()!=0)
        {
            if (devices.get(0) != null)
            {
                devices.get(0).setListener(deviceListener);
            }
            else
            {
                Toast.makeText(getActivity(), "没有查找到设备", Toast.LENGTH_SHORT).show();
            }
        }
    }
}