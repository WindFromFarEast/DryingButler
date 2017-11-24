package com.studio.dryingbutler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.api.GizWifiSDK;
import com.studio.dryingbutler.R;
import com.studio.dryingbutler.Utils.SharedUtil;
import com.studio.dryingbutler.application.MyApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;


/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: RealTimeMonitor
 * creator: WindFromFarEast
 * created time: 2017/9/23 12:50
 * description: 实时监控
 */

public class RealTimeMonitor extends Fragment
{
    private TextView tv_real_time_temperature_desc;
    private TextView tv_real_time_humidity_desc;
    private List<GizWifiDevice> devices = GizWifiSDK.sharedInstance().getDeviceList();
    private GizWifiDevice gizWifiDevice=devices.get(0);
    int sn=5;
    private ConcurrentHashMap<String, Object> command = new ConcurrentHashMap<String, Object> ();
    private Timer mTimer;
    private SeekBar seekBar_real_time_r;
    private SeekBar seekBar_real_time_g;
    private SeekBar seekBar_real_time_b;
    private SeekBar seekBar_real_time_motor;
    private int r=0;
    private int g=0;
    private int b=0;
    private TextView tv_real_time_r;
    private TextView tv_real_time_g;
    private TextView tv_real_time_b;
    private TextView tv_real_time_motor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_real_time_monitor,null);
        initView(view);
        return view;
    }

    private void initView(View view)
    {
        tv_real_time_temperature_desc= (TextView) view.findViewById(R.id.tv_real_time_temperature_desc);
        tv_real_time_humidity_desc= (TextView) view.findViewById(R.id.tv_real_time_humidity_desc);
        seekBar_real_time_r= (SeekBar) view.findViewById(R.id.seekBar_real_time_r);
        seekBar_real_time_g= (SeekBar) view.findViewById(R.id.seekBar_real_time_g);
        seekBar_real_time_b= (SeekBar) view.findViewById(R.id.seekBar_real_time_b);
        seekBar_real_time_motor= (SeekBar) view.findViewById(R.id.seekBar_real_time_motor);
        tv_real_time_r= (TextView) view.findViewById(R.id.tv_real_time_r);
        tv_real_time_g= (TextView) view.findViewById(R.id.tv_real_time_g);
        tv_real_time_b= (TextView) view.findViewById(R.id.tv_real_time_b);
        tv_real_time_motor= (TextView) view.findViewById(R.id.tv_real_time_motor);
        seekBar_real_time_motor.setProgress(5);

        seekBar_real_time_r.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                tv_real_time_r.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                r=seekBar.getProgress();
                changeLed(r,g,b);
            }
        });
        seekBar_real_time_g.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                tv_real_time_g.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                g=seekBar.getProgress();
                changeLed(r,g,b);
            }
        });
        seekBar_real_time_b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                tv_real_time_b.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                b=seekBar.getProgress();
                changeLed(r,g,b);
            }
        });
        seekBar_real_time_motor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                tv_real_time_motor.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                changeMotor(seekBar.getProgress()-5);
            }
        });

        mTimer=new Timer();
        setTimerTask();
    }

    private void setTimerTask()
    {
        mTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                gizWifiDevice.getDeviceStatus(null);
                try
                {
                    Thread.sleep(1000);//等待上个子线程结束
                    if(getActivity()!=null)
                    {
                        getActivity().runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                tv_real_time_temperature_desc.setText(SharedUtil.getStringData("Temperature") + "℃");
                                tv_real_time_humidity_desc.setText(SharedUtil.getStringData("Humidity") + "%hf");
                            }
                        });
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        },1000,20000);//每隔20s检测一次温湿度
    }

    private void changeLed(int r,int g,int b)
    {
        command.put("LED_R",r);
        command.put("LED_G",g);
        command.put("LED_B",b);
        devices.get(0).write(command,sn);
    }

    private void changeMotor(int speed)
    {
        command.put("Motor_Speed",speed);
        devices.get(0).write(command,1);
    }
}
