package com.studio.dryingbutler.fragment;

import android.app.Activity;
import android.content.Context;
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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
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
    private List<GizWifiDevice> devices = GizWifiSDK.sharedInstance().getDeviceList();
    private GizWifiDevice gizWifiDevice=devices.get(0);
    int sn=5;
    private ConcurrentHashMap<String, Object> command = new ConcurrentHashMap<String, Object> ();
    private Timer mTimer=new Timer();
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
    private LineChart chart_temp;
    private LineChart chart_humidity;
    private List<Entry> tempEntryList=new ArrayList<>();
    private List<Entry> humidityEntryList=new ArrayList<>();
    private LineDataSet tempDataSet;
    private LineData tempData;
    private LineDataSet humidityDataSet;
    private LineData humidityData;
    private Activity activity;

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
        seekBar_real_time_r= (SeekBar) view.findViewById(R.id.seekBar_real_time_r);
        seekBar_real_time_g= (SeekBar) view.findViewById(R.id.seekBar_real_time_g);
        seekBar_real_time_b= (SeekBar) view.findViewById(R.id.seekBar_real_time_b);
        seekBar_real_time_motor= (SeekBar) view.findViewById(R.id.seekBar_real_time_motor);
        tv_real_time_r= (TextView) view.findViewById(R.id.tv_real_time_r);
        tv_real_time_g= (TextView) view.findViewById(R.id.tv_real_time_g);
        tv_real_time_b= (TextView) view.findViewById(R.id.tv_real_time_b);
        tv_real_time_motor= (TextView) view.findViewById(R.id.tv_real_time_motor);
        chart_temp= (LineChart) view.findViewById(R.id.chart_temp);
        chart_humidity= (LineChart) view.findViewById(R.id.chart_humidity);
        seekBar_real_time_motor.setProgress(5);
        setTempChart();
        setHumidityChart();
        setTimer();

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

    private void setTempChart()
    {
        String temp=SharedUtil.getStringData("Temperature");
        if (!temp.isEmpty())
        {
            tempEntryList.add(new Entry(1,Integer.parseInt(temp)));
            tempDataSet=new LineDataSet(tempEntryList,"温度");
            tempDataSet.setLineWidth(1.75f);
            tempDataSet.setCircleSize(3f);
            chart_temp.animateX(3000);
            chart_temp.animateY(3000);
            tempData=new LineData(tempDataSet);
            chart_temp.setData(tempData);
            chart_temp.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            chart_temp.getAxisRight().setEnabled(false);
            chart_temp.getXAxis().setEnabled(false);
            chart_temp.getXAxis().setDrawGridLines(false);
            chart_temp.invalidate();
        }
    }

    private void setHumidityChart()
    {
        String humidity=SharedUtil.getStringData("Humidity");
        if (!humidity.isEmpty())
        {
            humidityEntryList.add(new Entry(1,Integer.parseInt(humidity)));
            humidityDataSet=new LineDataSet(humidityEntryList,"湿度");
            humidityDataSet.setLineWidth(1.75f);
            humidityDataSet.setCircleSize(3f);
            chart_humidity.animateX(3000);
            chart_humidity.animateY(3000);
            humidityData=new LineData(humidityDataSet);
            chart_humidity.setData(humidityData);
            chart_humidity.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            chart_humidity.getAxisRight().setEnabled(false);
            chart_humidity.getXAxis().setEnabled(false);
            chart_humidity.getXAxis().setDrawGridLines(false);
            chart_humidity.invalidate();
        }
    }

    private void setTimer()
    {
        mTimer.schedule(new TimerTask()
        {
            int tempX=2;
            int humidityX=2;
            @Override
            public void run()
            {
                int temp=Integer.parseInt(SharedUtil.getStringData("Temperature"));
                int humidity=Integer.parseInt(SharedUtil.getStringData("Humidity"));
                tempDataSet.addEntry(new Entry(tempX,temp));
                tempData.notifyDataChanged();
                chart_temp.notifyDataSetChanged();
                humidityDataSet.addEntry(new Entry(humidityX,humidity));
                humidityData.notifyDataChanged();
                chart_humidity.notifyDataSetChanged();
                tempX++;
                humidityX++;
                activity.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        chart_temp.invalidate();
                        chart_humidity.invalidate();
                    }
                });
            }
        },3000,5000);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        activity=(Activity) context;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mTimer.cancel();
        activity=null;
    }
}
