package com.studio.dryingbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
    private ImageView mapView;
    private Button btn_service_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_service);
        initView();
    }

    private void initView()
    {
        btn_service_back= (Button) findViewById(R.id.btn_service_back);
        mapView= (ImageView) findViewById(R.id.mapView);
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
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }
}
