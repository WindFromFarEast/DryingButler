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
import com.studio.dryingbutler.ui.CommunicationActivity;
import com.studio.dryingbutler.ui.FactoryActivity;
import com.studio.dryingbutler.ui.ServiceActivity;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: Discovery
 * creator: WindFromFarEast
 * created time: 2017/9/5 17:26
 * description: 发现页面
 */

public class Discovery extends Fragment implements View.OnClickListener
{
    private LinearLayout ll_discovery_call_factory;
    private LinearLayout ll_discovery_service;
    private LinearLayout ll_discovery_community;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_discovery,null);
        initView(view);
        return view;
    }

    private void initView(View view)
    {
        ll_discovery_call_factory= (LinearLayout) view.findViewById(R.id.ll_discovery_call_factory);
        ll_discovery_service= (LinearLayout) view.findViewById(R.id.ll_discovery_service);
        ll_discovery_community= (LinearLayout) view.findViewById(R.id.ll_discovery_community);

        ll_discovery_call_factory.setOnClickListener(this);
        ll_discovery_service.setOnClickListener(this);
        ll_discovery_community.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.ll_discovery_call_factory:
            {
                startActivity(new Intent(getActivity(), FactoryActivity.class));
                break;
            }
            case R.id.ll_discovery_service:
            {
                startActivity(new Intent(getActivity(), ServiceActivity.class));
                break;
            }
            case R.id.ll_discovery_community:
            {
                startActivity(new Intent(getActivity(), CommunicationActivity.class));
                break;
            }
        }
    }
}
