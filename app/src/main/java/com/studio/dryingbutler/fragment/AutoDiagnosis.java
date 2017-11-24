package com.studio.dryingbutler.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.api.GizWifiSDK;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.gizwits.gizwifisdk.listener.GizWifiDeviceListener;
import com.studio.dryingbutler.R;
import com.studio.dryingbutler.Utils.SharedUtil;
import com.studio.dryingbutler.adapter.StateShowListAdapter;
import com.studio.dryingbutler.entity.Device;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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
    private Device device=new Device();
    private StateShowListAdapter adapter;
    private List<GizWifiDevice> devices = GizWifiSDK.sharedInstance().getDeviceList();
    private GizWifiDevice gizWifiDevice=devices.get(0);
    private int flag=0;
    private Activity activity=getActivity();
    private Button btn_warning_motor_no;
    private Button btn_warning_motor_yes;
    private Calendar calendar=Calendar.getInstance();
    private GizWifiDeviceListener gizWifiDeviceListener=new GizWifiDeviceListener()
    {
        @Override
        public void didReceiveData(GizWifiErrorCode result, GizWifiDevice device, ConcurrentHashMap<String, Object> dataMap, int sn)
        {
            if (result==GizWifiErrorCode.GIZ_SDK_SUCCESS)
            {
                if (sn==1)
                {
                    updateList("电机转速已改变");
                }
                if (sn==5)
                {
                    updateList("LED的RBG值已改变");
                }
                if (dataMap.get("data")!=null)
                {
                    ConcurrentHashMap<String, Object> map = (ConcurrentHashMap<String, Object>) dataMap.get("data");
                    Toast.makeText(activity,"Temperature:"+map.get("Temperature").toString()+"\n"
                            +"Humidity:"+map.get("Humidity"),Toast.LENGTH_SHORT).show();
                    SharedUtil.saveStringData("Temperature",map.get("Temperature").toString());
                    SharedUtil.saveStringData("Humidity",map.get("Humidity").toString());
                    if ((boolean)(map.get("Infrared"))&&flag==0)
                    {
                        Toast.makeText(activity,"警告：电机出现异常，请检查",Toast.LENGTH_SHORT).show();
                        //弹出警告
                        openWindow();
                        flag=1;//标志到出错位
                    }
                    else if (!((boolean)(map.get("Infrared"))))
                    {
                        flag=0;
                    }
                }
            }
        }
    };

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
        adapter=new StateShowListAdapter(mList,getActivity());
        lv_state_show.setAdapter(adapter);

        gizWifiDevice.setListener(gizWifiDeviceListener);
    }

    private void fillList()
    {
        device=new Device();
        device.setName("A");
        device.setWork(true);
        device.setFanWork(true);
        device.setBatteryWork(true);
        device.setCirFanOn(true);
        device.setBlowerOn(true);
        device.setAxuFanOn(true);
        device.setFire(true);
        device.setCoolOneOn(true);
        device.setCoolTwoOn(true);
        device.setValveOneOn(true);
        device.setValveTwoOn(true);
        device.setProcess(20);
    }

    private void updateList(String desc)
    {
        device.setDesc(desc);
        device.setTime(""+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
        mList.add(device);
        adapter.notifyDataSetChanged();
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
            }
        });
        btn_warning_motor_yes.setOnClickListener(new View.OnClickListener()
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
    public void onAttach(Context context)
    {
        super.onAttach(context);
        activity= (Activity) context;
    }
}
