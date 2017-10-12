package com.studio.dryingbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.adapter.ViewPagerAdapter;
import com.studio.dryingbutler.fragment.GuideOne;
import com.studio.dryingbutler.fragment.GuideThree;
import com.studio.dryingbutler.fragment.GuideTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.ui
 * file name: GuideActivity
 * creator: WindFromFarEast
 * created time: 2017/8/29 12:26
 * description: 引导页
 */

public class GuideActivity extends AppCompatActivity
{
    private Button btn_guide_exp;
    private ViewPager vp_guide;
    private ImageView v_guide_point1;
    private ImageView v_guide_point2;
    private ImageView v_guide_point3;
    private List<Fragment> fragmentList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //初始化控件和引导页滑动逻辑
        initView();
    }

    private void initView()
    {
        btn_guide_exp = (Button) findViewById(R.id.btn_guide_exp);
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        v_guide_point1 = (ImageView) findViewById(R.id.iv_guide_point1);
        v_guide_point2 = (ImageView) findViewById(R.id.iv_guide_point2);
        v_guide_point3 = (ImageView) findViewById(R.id.iv_guide_point3);
        //填充数据源
        fillingList();
        //ViewPager绑定适配器
        vp_guide.setOffscreenPageLimit(2);
        vp_guide.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        //监听ViewPager滑动,改变小圆点和矩形图片
        vp_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
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
                        setPointImg(position);
                        break;
                    }
                    case 1:
                    {
                        setPointImg(position);
                        break;
                    }
                    case 2:
                    {
                        setPointImg(position);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
        //首次进入默认在第一个Fragment所以要将第一个原点设置为矩形
        setPointImg(0);
        //为体验按钮设置点击事件
        btn_guide_exp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(GuideActivity.this,LoginActivity.class));
                //进入到登录页面后销毁引导页
                finish();
            }
        });
    }

    private void fillingList()
    {
        Fragment guide1, guide2, guide3;
        guide1 = new GuideOne();
        guide2 = new GuideTwo();
        guide3 = new GuideThree();
        fragmentList.add(guide1);
        fragmentList.add(guide2);
        fragmentList.add(guide3);
    }

    private void setPointImg(int position)
    {
        if (position==0)
        {
            v_guide_point1.setImageResource(R.drawable.long_point);
            v_guide_point2.setImageResource(R.drawable.point);
            v_guide_point3.setImageResource(R.drawable.point);
        }
        else if (position==1)
        {
            v_guide_point1.setImageResource(R.drawable.point);
            v_guide_point2.setImageResource(R.drawable.long_point);
            v_guide_point3.setImageResource(R.drawable.point);
        }
        else if (position==2)
        {
            v_guide_point1.setImageResource(R.drawable.point);
            v_guide_point2.setImageResource(R.drawable.point);
            v_guide_point3.setImageResource(R.drawable.long_point);
        }
    }
}
