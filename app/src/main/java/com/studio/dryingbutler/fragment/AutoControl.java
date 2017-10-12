package com.studio.dryingbutler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.dryingbutler.R;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: AutoControl
 * creator: WindFromFarEast
 * created time: 2017/9/23 12:54
 * description: TODO
 */

public class AutoControl extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_auto_control,null);
        return view;
    }
}
