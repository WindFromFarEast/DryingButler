package com.studio.dryingbutler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.adapter.MyAppointmentListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: MyAppointment
 * creator: WindFromFarEast
 * created time: 2017/9/15 16:52
 * description: 我的预约界面
 */

public class MyAppointment extends Fragment
{
    private String fragmentName;
    private ListView lv_my_appointment;
    private List<com.studio.dryingbutler.entity.MyAppointment> mList=new ArrayList<>();
    private MyAppointmentListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_my_appointment,null);
        initView(view);
        return view;
    }

    private void initView(View view)
    {
        lv_my_appointment= (ListView) view.findViewById(R.id.lv_my_appointment);
        fillingList();
        adapter=new MyAppointmentListAdapter(mList,getActivity());
        lv_my_appointment.setAdapter(adapter);
        lv_my_appointment.setDivider(null);
    }

    private void fillingList()
    {
        com.studio.dryingbutler.entity.MyAppointment myAppointment1=new com.studio.dryingbutler.entity.MyAppointment("XXX","XXX","未处理");
        com.studio.dryingbutler.entity.MyAppointment myAppointment2=new com.studio.dryingbutler.entity.MyAppointment("XXX","XXX","处理中");
        com.studio.dryingbutler.entity.MyAppointment myAppointment3=new com.studio.dryingbutler.entity.MyAppointment("XXX","XXX","已处理");
        mList.add(myAppointment1);
        mList.add(myAppointment2);
        mList.add(myAppointment3);
    }

    public void setFragmentName(String fragmentName)
    {
        this.fragmentName=fragmentName;
    }

    public String getFragmentName()
    {
        return fragmentName;
    }
}
