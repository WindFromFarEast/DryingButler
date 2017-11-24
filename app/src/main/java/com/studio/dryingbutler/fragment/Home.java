package com.studio.dryingbutler.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.studio.dryingbutler.MainActivity;
import com.studio.dryingbutler.R;
import com.studio.dryingbutler.Utils.MyLog;
import com.studio.dryingbutler.Utils.PermissionUtil;
import com.studio.dryingbutler.adapter.DeviceListAdapter;
import com.studio.dryingbutler.application.MyApplication;
import com.studio.dryingbutler.entity.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: Home
 * creator: WindFromFarEast
 * created time: 2017/9/5 17:24
 * description: Home
 */

public class Home extends Fragment
{
    //声明布局控件
    private TextView tv_home_head;
    private TextView tv_home_time;
    private TextView tv_home_date;
    private TextView tv_home_location;
    //设备列表
    private ListView lv_home_device;
    //设备列表数据源及适配器
    private List<Device> mList=new ArrayList<>();
    private DeviceListAdapter listAdapter;
    //经纬度
    private double longtitude,latitude;
    private LocationClient mLocationClient=new LocationClient(MyApplication.getContext());
    //位置信息回调接口,获取到位置信息后在此进行逻辑处理
    private BDLocationListener mListener=new BDLocationListener()
    {
        @Override
        public void onReceiveLocation(final BDLocation bdLocation)
        {
            //获取经纬度
            latitude=bdLocation.getLatitude();
            longtitude=bdLocation.getLongitude();
            //根据经纬度获取位置天气信息
            RxVolley.get("https://api.seniverse.com/v3/weather/now.json?key=wc9dbz12jlelbsfc&location="
                            +latitude+":"+longtitude+"&language=zh-Hans&unit=c"
                    , new HttpCallback()
                    {
                        @Override
                        public void onSuccess(String t)
                        {
                            //成功获取到天气Json数据和位置信息,显示到界面上
                            tv_home_location.setText(bdLocation.getDistrict()+"，"+bdLocation.getCity());
                        }
                    });
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_home,null);
        //初始化控件
        initView(view);
        //获取地理位置权限,获取权限后在回调方法中处理逻辑,6.0之前则不需要运行时权限处理
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if (PermissionUtil.requestPermission(getActivity(), PermissionUtil.LOCATION_CODE, Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE) == 0)
            {
                initLocation();
            }
        }
        else
        {
            initLocation();
        }
        //
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            //获取overlays权限
            if (!Settings.canDrawOverlays(getActivity()))
            {
                startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:"+getActivity().getPackageName())),2);
            }
        }
        //完成设备列表
        addToList();
        showDeviceList();
        return view;
    }

    private void initView(View view)
    {
        tv_home_date= (TextView) view.findViewById(R.id.tv_home_date);
        tv_home_head= (TextView) view.findViewById(R.id.tv_home_head);
        tv_home_location= (TextView) view.findViewById(R.id.tv_home_location);
        tv_home_time= (TextView) view.findViewById(R.id.tv_home_time);
        lv_home_device= (ListView) view.findViewById(R.id.lv_home_device);
    }

    private void initLocation()
    {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span=0;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        //为LocationClient注册监听器
        mLocationClient.registerLocationListener(mListener);
        //开始定位
        mLocationClient.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case PermissionUtil.LOCATION_CODE:
            {
                for (int i=0;i<grantResults.length;i++)
                {
                    if (grantResults[i]!= PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(getActivity(),"必须同意所有权限才能使用本软件",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                initLocation();
                break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case 2:
            {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    if (Settings.canDrawOverlays(getActivity()))
                    {
                        Toast.makeText(getActivity(),"Overlays权限已打开",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"Overlays权限未打开，将无法收到警告",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }
        }
    }

    //填充设备列表数据源
    private void addToList()
    {
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

        Device device2=new Device();
        device2.setName("B");
        device2.setWork(true);
        device2.setFanWork(true);
        device2.setBatteryWork(true);
        device2.setCirFanOn(true);
        device2.setBlowerOn(true);
        device2.setAxuFanOn(false);
        device2.setFire(true);
        device2.setCoolOneOn(false);
        device2.setCoolTwoOn(true);
        device2.setValveOneOn(true);
        device2.setValveTwoOn(false);
        device2.setProcess(30);
        mList.add(device2);

        MyLog.printInfo("Home列表长度:"+mList.size());
    }
    //显示设备列表
    private void showDeviceList()
    {
        listAdapter=new DeviceListAdapter(mList,getActivity());
        lv_home_device.setAdapter(listAdapter);
        //取消分割线
        lv_home_device.setDivider(null);
    }
}
