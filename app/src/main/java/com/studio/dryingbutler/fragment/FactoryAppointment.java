package com.studio.dryingbutler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.studio.dryingbutler.R;

import java.util.ArrayList;
import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: FactoryAppointment
 * creator: WindFromFarEast
 * created time: 2017/9/15 16:48
 * description: 预约厂家界面
 */

public class FactoryAppointment extends Fragment
{
    private String fragmentName;
    private Spinner spinner_factory_appointment;
    private List<String> spinnerList=new ArrayList<>();
    private ArrayAdapter spinnerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_appointment_factory,null);
        initView(view);
        return view;
    }

    private void initView(View view)
    {
        spinner_factory_appointment = (Spinner) view.findViewById(R.id.spinner_factory_appointment);
        fillList();
        spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(R.layout.my_simple_spinner_dropdown_item);
        spinner_factory_appointment.setAdapter(spinnerAdapter);
        spinner_factory_appointment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                spinner_factory_appointment.setBackgroundResource(R.drawable.spinner_bg);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                spinner_factory_appointment.setBackgroundResource(R.drawable.spinner_bg);
            }
        });
    }

    private void fillList()
    {
        spinnerList.add("设备故障");
        spinnerList.add("烘干效果不理想");
        spinnerList.add("其他");
    }

    public void setFragmentName(String fragmentName)
    {
        this.fragmentName=fragmentName;
    }

    public String getFragmentName()
    {
        return fragmentName;
    }
}
