package com.studio.dryingbutler.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.ui.AboutActivity;
import com.studio.dryingbutler.ui.NoviceGuidanceActivity;
import com.studio.dryingbutler.ui.SettingActivity;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: Me
 * creator: WindFromFarEast
 * created time: 2017/9/5 17:26
 * description: 我的界面
 */

public class Me extends Fragment implements View.OnClickListener
{
    private LinearLayout ll_me_setting;
    private LinearLayout ll_me_guide;
    private LinearLayout ll_me_about;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_me,null);
        initView(view);
        return view;
    }

    private void initView(View view)
    {
        ll_me_about= (LinearLayout) view.findViewById(R.id.ll_me_about);
        ll_me_guide= (LinearLayout) view.findViewById(R.id.ll_me_guide);
        ll_me_setting= (LinearLayout) view.findViewById(R.id.ll_me_setting);

        ll_me_setting.setOnClickListener(this);
        ll_me_about.setOnClickListener(this);
        ll_me_guide.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.ll_me_setting:
            {
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            }
            case R.id.ll_me_about:
            {
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            }
            case R.id.ll_me_guide:
            {
                startActivity(new Intent(getActivity(), NoviceGuidanceActivity.class));
                break;
            }
        }
    }
}
