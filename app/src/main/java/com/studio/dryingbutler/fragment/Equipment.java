package com.studio.dryingbutler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.studio.dryingbutler.R;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: Equipment
 * creator: WindFromFarEast
 * created time: 2017/9/5 17:21
 * description: 设备页面
 */

public class Equipment extends Fragment implements View.OnClickListener
{
    private Button btn_equipment_empty_add;
    private Button btn_equipment_cancel_add;
    private RelativeLayout rl_equipment_empty;
    private RelativeLayout rl_equipment_add;
    private EditText et_equipment_number;
    private EditText et_equipment_code;
    private EditText et_equipment_alias;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_equipment,null);
        initView(view);
        return view;
    }

    private void initView(View view)
    {
        btn_equipment_cancel_add= (Button) view.findViewById(R.id.btn_equipment_cancel_add);
        btn_equipment_empty_add= (Button) view.findViewById(R.id.btn_equipment_empty_add);
        rl_equipment_empty= (RelativeLayout) view.findViewById(R.id.rl_equipment_empty);
        rl_equipment_add= (RelativeLayout) view.findViewById(R.id.rl_equipment_add);
        et_equipment_alias= (EditText) view.findViewById(R.id.et_equipment_alias);
        et_equipment_number= (EditText) view.findViewById(R.id.et_equipment_number);
        et_equipment_code= (EditText) view.findViewById(R.id.et_equipment_code);

        btn_equipment_empty_add.setOnClickListener(this);
        btn_equipment_cancel_add.setOnClickListener(this);


    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_equipment_empty_add:
            {
                rl_equipment_empty.setVisibility(View.GONE);
                rl_equipment_add.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.btn_equipment_cancel_add:
            {
                rl_equipment_add.setVisibility(View.GONE);
                rl_equipment_empty.setVisibility(View.VISIBLE);
                break;
            }
        }
    }
}
