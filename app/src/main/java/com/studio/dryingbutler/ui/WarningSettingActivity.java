package com.studio.dryingbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.studio.dryingbutler.R;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.ui
 * file name: WarningSettingActivity
 * creator: WindFromFarEast
 * created time: 2017/9/11 16:51
 * description: 报警设置页面
 */

public class WarningSettingActivity extends AppCompatActivity implements View.OnClickListener
{
    private Switch switch_warning_setting_open_all;
    private Switch switch_warning_setting_up;
    private Switch switch_warning_setting_down;
    private Switch switch_warning_setting_fan;
    private Switch switch_warning_setting_light;
    private Switch switch_warning_setting_something;
    private Button btn_setting_warning_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning_setting);
        initView();
    }

    private void initView()
    {
        switch_warning_setting_open_all= (Switch) findViewById(R.id.switch_warning_setting_open_all);
        switch_warning_setting_up= (Switch) findViewById(R.id.switch_warning_setting_up);
        switch_warning_setting_down= (Switch) findViewById(R.id.switch_warning_setting_down);
        switch_warning_setting_fan= (Switch) findViewById(R.id.switch_warning_setting_fan);
        switch_warning_setting_light= (Switch) findViewById(R.id.switch_warning_setting_light);
        switch_warning_setting_something= (Switch) findViewById(R.id.switch_warning_setting_something);
        btn_setting_warning_back= (Button) findViewById(R.id.btn_setting_warning_back);

        switch_warning_setting_open_all.setOnClickListener(this);
        btn_setting_warning_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.switch_warning_setting_open_all:
            {
                if (switch_warning_setting_open_all.isChecked())
                {
                    switch_warning_setting_up.setChecked(true);
                    switch_warning_setting_down.setChecked(true);
                    switch_warning_setting_fan.setChecked(true);
                    switch_warning_setting_light.setChecked(true);
                    switch_warning_setting_light.setChecked(true);
                    switch_warning_setting_something.setChecked(true);
                }
                else
                {
                    switch_warning_setting_up.setChecked(false);
                    switch_warning_setting_down.setChecked(false);
                    switch_warning_setting_fan.setChecked(false);
                    switch_warning_setting_light.setChecked(false);
                    switch_warning_setting_light.setChecked(false);
                    switch_warning_setting_something.setChecked(false);
                }
                break;
            }
            case R.id.btn_setting_warning_back:
            {
                onBackPressed();
                break;
            }
        }
    }
}
