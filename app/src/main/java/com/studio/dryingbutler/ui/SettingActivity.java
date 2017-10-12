package com.studio.dryingbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.studio.dryingbutler.R;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.ui
 * file name: SettingActivity
 * creator: WindFromFarEast
 * created time: 2017/9/11 14:01
 * description: 设置页面
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btn_setting_back;
    private LinearLayout ll_setting_warning;
    private LinearLayout ll_setting_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView()
    {
        btn_setting_back= (Button) findViewById(R.id.btn_setting_back);
        ll_setting_warning= (LinearLayout) findViewById(R.id.ll_setting_warning);
        ll_setting_password= (LinearLayout) findViewById(R.id.ll_setting_password);

        btn_setting_back.setOnClickListener(this);
        ll_setting_warning.setOnClickListener(this);
        ll_setting_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_setting_back:
            {
                onBackPressed();
                break;
            }
            case R.id.ll_setting_warning:
            {
                startActivity(new Intent(this,WarningSettingActivity.class));
                break;
            }
            case R.id.ll_setting_password:
            {
                startActivity(new Intent(this,PasswordActivity.class));
                break;
            }
        }
    }
}
