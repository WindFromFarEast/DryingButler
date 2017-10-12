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
import com.studio.dryingbutler.fragment.Communication;
import com.studio.dryingbutler.fragment.MyPost;

import java.util.ArrayList;
import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.ui
 * file name: CommunicationActivity
 * creator: WindFromFarEast
 * created time: 2017/9/18 16:34
 * description: 社区交流界面
 */

public class CommunicationActivity extends AppCompatActivity
{
    private Button btn_communication_back;
    private TabLayout tl_communication;
    private ViewPager vp_communication;
    private List<Fragment> mList=new ArrayList<>();
    private List<String> tabTitles=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);
        initView();
    }

    private void initView()
    {
        btn_communication_back= (Button) findViewById(R.id.btn_communication_back);
        tl_communication= (TabLayout) findViewById(R.id.tl_communication);
        vp_communication= (ViewPager) findViewById(R.id.vp_communication);

        btn_communication_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        fillTabTitles();
        fillList();

        vp_communication.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager())
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

        tl_communication.setupWithViewPager(vp_communication);
    }

    private void fillList()
    {
        mList.add(new Communication());
        mList.add(new MyPost());
    }

    private void fillTabTitles()
    {
        tabTitles.add("逛社区");
        tabTitles.add("我的帖子");
    }
}
