package com.studio.dryingbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.studio.dryingbutler.R;
import com.studio.dryingbutler.application.MyApplication;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.ui
 * file name: ServiceActivity
 * creator: WindFromFarEast
 * created time: 2017/9/17 14:57
 * description: 维修服务页面
 */

public class ServiceActivity extends AppCompatActivity
{
    private MapView mapView;
    private Button btn_service_back;
    //经纬度
    private double longtitude,latitude;
    private BaiduMap baiduMap;
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
            LatLng ll=new LatLng(latitude,longtitude);
            MapStatusUpdate update=MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            MyLocationData.Builder locationBuilder=new MyLocationData.Builder();
            locationBuilder.longitude(longtitude);
            locationBuilder.latitude(latitude);
            MyLocationData locationData=locationBuilder.build();
            baiduMap.setMyLocationData(locationData);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_service);
        initView();
        initLocation();
    }

    private void initView()
    {
        btn_service_back= (Button) findViewById(R.id.btn_service_back);
        mapView= (MapView) findViewById(R.id.mapView);
        baiduMap=mapView.getMap();
        baiduMap.setMyLocationEnabled(true);

        btn_service_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mapView.onPause();
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
}
