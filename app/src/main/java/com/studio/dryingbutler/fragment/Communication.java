package com.studio.dryingbutler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.adapter.ViewPagerAdapterForView;

import java.util.ArrayList;
import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: Communication
 * creator: WindFromFarEast
 * created time: 2017/9/18 16:50
 * description: 逛社区
 */

public class Communication extends Fragment
{
    private ViewPager vp_fragment_communication;
    private ViewPagerAdapterForView pagerAdapter;
    private List<View> viewList=new ArrayList<>();
    private View view1,view2,view3;
    private ImageView iv_fragment_communication_point1;
    private ImageView iv_fragment_communication_point2;
    private ImageView iv_fragment_communication_point3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_communication,null);
        initView(view);
        return view;
    }

    private void initView(View view)
    {
        vp_fragment_communication= (ViewPager) view.findViewById(R.id.vp_fragment_communication);
        iv_fragment_communication_point1= (ImageView) view.findViewById(R.id.iv_fragment_communication_point1);
        iv_fragment_communication_point2= (ImageView) view.findViewById(R.id.iv_fragment_communication_point2);
        iv_fragment_communication_point3= (ImageView) view.findViewById(R.id.iv_fragment_communication_point3);

        setImagePoint(0);
        fillingViewList();
        pagerAdapter=new ViewPagerAdapterForView(viewList);
        vp_fragment_communication.setAdapter(pagerAdapter);
        vp_fragment_communication.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                setImagePoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    private void fillingViewList()
    {
        view1=LayoutInflater.from(getActivity()).inflate(R.layout.activity_communication_pager_one,null);
        view2=LayoutInflater.from(getActivity()).inflate(R.layout.activity_communication_pager_two,null);
        view3=LayoutInflater.from(getActivity()).inflate(R.layout.activity_communication_pager_three,null);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
    }

    private void setImagePoint(int position)
    {
        switch (position)
        {
            case 0:
            {
                iv_fragment_communication_point1.setImageResource(R.drawable.point_blue);
                iv_fragment_communication_point2.setImageResource(R.drawable.point_grey);
                iv_fragment_communication_point3.setImageResource(R.drawable.point_grey);
                break;
            }
            case 1:
            {
                iv_fragment_communication_point1.setImageResource(R.drawable.point_grey);
                iv_fragment_communication_point2.setImageResource(R.drawable.point_blue);
                iv_fragment_communication_point3.setImageResource(R.drawable.point_grey);
                break;
            }
            case 2:
            {
                iv_fragment_communication_point1.setImageResource(R.drawable.point_grey);
                iv_fragment_communication_point2.setImageResource(R.drawable.point_grey);
                iv_fragment_communication_point3.setImageResource(R.drawable.point_blue);
                break;
            }
        }
    }
}
