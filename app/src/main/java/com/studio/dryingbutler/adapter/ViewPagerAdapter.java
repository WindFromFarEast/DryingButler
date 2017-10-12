package com.studio.dryingbutler.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.adapter
 * file name: ViewPagerAdapter
 * creator: WindFromFarEast
 * created time: 2017/8/31 13:27
 * description: ViewPager适配器
 */

public class ViewPagerAdapter extends FragmentPagerAdapter
{
    //数据源
    private List<Fragment> fragmentList;

    //构造方法,完成数据源的实例化
    public ViewPagerAdapter(FragmentManager fm,List<Fragment> fragmentList)
    {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        return fragmentList.size();
    }

}
