package com.studio.dryingbutler.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.adapter
 * file name: ViewPagerAdapterForView
 * creator: WindFromFarEast
 * created time: 2017/9/18 17:10
 * description: ViewPager适配器（For View）
 */

public class ViewPagerAdapterForView extends PagerAdapter
{
    private List<View> viewList;

    public ViewPagerAdapterForView(List<View> viewList)
    {
        this.viewList=viewList;
    }

    @Override
    public int getCount()
    {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }
}
