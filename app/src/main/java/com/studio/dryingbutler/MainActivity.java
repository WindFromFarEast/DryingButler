package com.studio.dryingbutler;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.studio.dryingbutler.adapter.ViewPagerAdapter;
import com.studio.dryingbutler.fragment.Discovery;
import com.studio.dryingbutler.fragment.Equipment;
import com.studio.dryingbutler.fragment.Home;
import com.studio.dryingbutler.fragment.Me;
import com.studio.dryingbutler.fragment.Monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler
 * file name: MainActivity
 * creator: WindFromFarEast
 * created time: 2017/8/31 14:20
 * description: 主界面
 */

public class MainActivity extends AppCompatActivity
{
    private ViewPager vp_home;
    //ViewPager数据源
    private List<Fragment> fragmentList=new ArrayList<>();
    //
    private ImageView iv_monitor;
    private ImageView iv_equipment;
    private ImageView iv_home;
    private ImageView iv_discover;
    private ImageView iv_me;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件实例
        initView();
        //设置ViewPager相关属性
        setViewPager();
        setBottomImage(2);
    }

    private void initView()
    {
        vp_home= (ViewPager) findViewById(R.id.vp_home);
        iv_discover= (ImageView) findViewById(R.id.iv_discovery);
        iv_equipment= (ImageView) findViewById(R.id.iv_equipment);
        iv_home= (ImageView) findViewById(R.id.iv_home);
        iv_me= (ImageView) findViewById(R.id.iv_me);
        iv_monitor= (ImageView) findViewById(R.id.iv_monitor);
    }

    private void fillingList()
    {
        Fragment fragment_monitor=new Monitor();
        Fragment fragment_equipment=new Equipment();
        Fragment fragment_home=new Home();
        Fragment fragment_discovery=new Discovery();
        Fragment fragment_me=new Me();
        fragmentList.add(fragment_monitor);
        fragmentList.add(fragment_equipment);
        fragmentList.add(fragment_home);
        fragmentList.add(fragment_discovery);
        fragmentList.add(fragment_me);
    }

    private void setViewPager()
    {
        //填充ViewPager适配器的数据源
        fillingList();
        //设置ViewPager适配器
        ViewPagerAdapter pagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),fragmentList);
        vp_home.setAdapter(pagerAdapter);
        //预加载
        vp_home.setOffscreenPageLimit(5);
        //进入界面默认在Home
        vp_home.setCurrentItem(2);
        //监听ViewPager的滑动事件
        vp_home.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                switch (position)
                {
                    case 0:
                    {
                        setBottomImage(position);
                        break;
                    }
                    case 1:
                    {
                        setBottomImage(position);
                        break;
                    }
                    case 2:
                    {
                        setBottomImage(position);
                        break;
                    }
                    case 3:
                    {
                        setBottomImage(position);
                        break;
                    }
                    case 4:
                    {
                        setBottomImage(position);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    //改变底部图片
    private void setBottomImage(int position)
    {
        if (position==0)
        {
            iv_monitor.setImageResource(R.drawable.monitor_selected);
            iv_me.setImageResource(R.drawable.me);
            iv_home.setImageResource(R.drawable.home);
            iv_equipment.setImageResource(R.drawable.equipment);
            iv_discover.setImageResource(R.drawable.discovery);
        }
        else if (position==1)
        {
            iv_monitor.setImageResource(R.drawable.monitor);
            iv_me.setImageResource(R.drawable.me);
            iv_home.setImageResource(R.drawable.home);
            iv_equipment.setImageResource(R.drawable.equipment_selected);
            iv_discover.setImageResource(R.drawable.discovery);
        }
        else if (position==2)
        {
            iv_monitor.setImageResource(R.drawable.monitor);
            iv_me.setImageResource(R.drawable.me);
            iv_home.setImageResource(R.drawable.home_selected);
            iv_equipment.setImageResource(R.drawable.equipment);
            iv_discover.setImageResource(R.drawable.discovery);
        }
        else if (position==3)
        {
            iv_monitor.setImageResource(R.drawable.monitor);
            iv_me.setImageResource(R.drawable.me);
            iv_home.setImageResource(R.drawable.home);
            iv_equipment.setImageResource(R.drawable.equipment);
            iv_discover.setImageResource(R.drawable.discovery_selected);
        }
        else if (position==4)
        {
            iv_monitor.setImageResource(R.drawable.monitor);
            iv_me.setImageResource(R.drawable.me_selected);
            iv_home.setImageResource(R.drawable.home);
            iv_equipment.setImageResource(R.drawable.equipment);
            iv_discover.setImageResource(R.drawable.discovery);
        }
    }
}
