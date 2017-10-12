package com.studio.dryingbutler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.adapter.StateShowListAdapter;
import com.studio.dryingbutler.entity.Device;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: AutoDiagnosis
 * creator: WindFromFarEast
 * created time: 2017/9/21 14:33
 * description: 自动诊断页面
 */

public class AutoDiagnosis extends Fragment implements View.OnClickListener
{
    private LinearLayout ll_health_assessment;
    private LinearLayout ll_health_assessment_content;
    private LinearLayout ll_state_show;
    private ListView lv_state_show;
    private ImageView iv_state_show;
    private ImageView iv_health_assessment;
    private List<Device> mList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_diagnosis,null);
        initView(view);
        return view;
    }

    private void initView(View view)
    {
        ll_health_assessment= (LinearLayout) view.findViewById(R.id.ll_health_assessment);
        ll_health_assessment_content= (LinearLayout) view.findViewById(R.id.ll_health_assessment_content);
        iv_health_assessment= (ImageView) view.findViewById(R.id.iv_health_assessment);
        ll_state_show= (LinearLayout) view.findViewById(R.id.ll_state_show);
        lv_state_show= (ListView) view.findViewById(R.id.lv_state_show);
        iv_state_show= (ImageView) view.findViewById(R.id.iv_state_show);
        lv_state_show.setDivider(null);

        ll_health_assessment.setOnClickListener(this);
        ll_state_show.setOnClickListener(this);

        fillList();
        StateShowListAdapter adapter=new StateShowListAdapter(mList,getActivity());
        lv_state_show.setAdapter(adapter);
    }

    private void fillList()
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
        Calendar calendar=Calendar.getInstance();
        device1.setTime(""+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
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
        device2.setTime(""+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
        mList.add(device2);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.ll_health_assessment:
            {
                if (ll_health_assessment_content.getVisibility()==View.GONE)
                {
                    ll_health_assessment_content.setVisibility(View.VISIBLE);
                    iv_health_assessment.setImageResource(R.drawable.btn_my_posted_pushed);
                }
                else
                {
                    ll_health_assessment_content.setVisibility(View.GONE);
                    iv_health_assessment.setImageResource(R.drawable.btn_my_posted);
                }
                break;
            }
            case R.id.ll_state_show:
            {
                if (lv_state_show.getVisibility()==View.GONE)
                {
                    lv_state_show.setVisibility(View.VISIBLE);
                    iv_state_show.setImageResource(R.drawable.btn_my_posted_pushed);
                }
                else
                {
                    lv_state_show.setVisibility(View.GONE);
                    iv_state_show.setImageResource(R.drawable.btn_my_posted);
                }
                break;
            }
        }
    }
}
