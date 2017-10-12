package com.studio.dryingbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.fragment.AutoControl;
import com.studio.dryingbutler.fragment.AutoDiagnosis;
import com.studio.dryingbutler.fragment.RealTimeMonitor;

import java.util.ArrayList;
import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.ui
 * file name: MonitorActivity
 * creator: WindFromFarEast
 * created time: 2017/9/22 16:26
 * description: TODO
 */

public class MonitorActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btn_monitor_activity_back;
    private TabLayout tl_monitor_activity;
    private ViewPager vp_monitor_activity;
    private List<Fragment> mList=new ArrayList<>();
    private List<String> tabTitles=new ArrayList<>();
    private TextView tv_monitor_activity_device_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        initView();
        addToList();
        setViewPagerAndTabLayout();
    }

    private void setViewPagerAndTabLayout()
    {
        vp_monitor_activity.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                return mList.get(position);
            }

            @Override
            public int getCount()
            {
                return mList.size();
            }

            @Override
            public CharSequence getPageTitle(int position)
            {
                return tabTitles.get(position);
            }
        });

        tl_monitor_activity.setupWithViewPager(vp_monitor_activity);
    }

    private void addToList()
    {
        AutoDiagnosis autoDiagnosis=new AutoDiagnosis();
        RealTimeMonitor realTimeMonitor=new RealTimeMonitor();
        AutoControl autoControl=new AutoControl();
        mList.add(autoDiagnosis);
        mList.add(realTimeMonitor);
        mList.add(autoControl);

        tabTitles.add("自动诊断");
        tabTitles.add("实时监控");
        tabTitles.add("自动控制");
    }

    private void initView()
    {
        btn_monitor_activity_back= (Button) findViewById(R.id.btn_monitor_activity_back);
        tl_monitor_activity= (TabLayout) findViewById(R.id.tl_monitor_activity);
        vp_monitor_activity= (ViewPager) findViewById(R.id.vp_monitor_activity);
        tv_monitor_activity_device_name= (TextView) findViewById(R.id.tv_monitor_activity_device_name);

        btn_monitor_activity_back.setOnClickListener(this);
        tv_monitor_activity_device_name.setText("设备"+getIntent().getStringExtra("deviceName")+"");
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_monitor_activity_back:
            {
                finish();
                break;
            }
        }
    }

}
