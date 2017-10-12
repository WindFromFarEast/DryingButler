package com.studio.dryingbutler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.Utils.MyLog;
import com.studio.dryingbutler.adapter.MonitorDeviceAdapter;
import com.studio.dryingbutler.entity.Device;

import java.util.ArrayList;
import java.util.List;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_monitor,null);
        initView(view);
        addToList();
        setListView();
        return view;
    }

    private void initView(View view)
    {
        lv_monitor_device= (ListView) view.findViewById(R.id.lv_monitor_device);
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
        device2.setWork(false);
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

        MyLog.printInfo("列表长度:"+mList.size());
    }

    private void setListView()
    {
        adapter = new MonitorDeviceAdapter(mList, getActivity());
        lv_monitor_device.setAdapter(adapter);
        lv_monitor_device.setDivider(null);
    }
}