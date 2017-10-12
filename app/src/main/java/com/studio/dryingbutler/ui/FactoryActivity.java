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

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.fragment.FactoryAppointment;
import com.studio.dryingbutler.fragment.MyAppointment;

import java.util.ArrayList;
import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.ui
 * file name: FactoryActivity
 * creator: WindFromFarEast
 * created time: 2017/9/15 16:33
 * description: 联系厂家界面
 */

public class FactoryActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btn_factory_back;
    private TabLayout tl_factory;
    private ViewPager vp_factory;
    private List<Fragment> mList=new ArrayList<>();
    private List<String> tabTitles=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);
        initView();
        fillList();
        setViewPagerAdapter();
    }

    private void initView()
    {
        btn_factory_back= (Button) findViewById(R.id.btn_factory_back);
        tl_factory= (TabLayout) findViewById(R.id.tl_factory);
        vp_factory= (ViewPager) findViewById(R.id.vp_factory);

        btn_factory_back.setOnClickListener(this);
    }

    private void fillList()
    {
        FactoryAppointment factoryAppointment=new FactoryAppointment();
        factoryAppointment.setFragmentName("预约厂家");
        tabTitles.add("预约厂家");
        MyAppointment myAppointment=new MyAppointment();
        myAppointment.setFragmentName("我的预约");
        tabTitles.add("我的预约");

        mList.add(factoryAppointment);
        mList.add(myAppointment);

        setTabLayout();
    }


    private void setTabLayout()
    {
        tl_factory.setupWithViewPager(vp_factory);
    }

    private void setViewPagerAdapter()
    {
        vp_factory.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager())
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
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_factory_back:
            {
                onBackPressed();
                break;
            }
        }
    }
}
