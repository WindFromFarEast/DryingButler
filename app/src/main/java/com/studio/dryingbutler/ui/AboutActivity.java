package com.studio.dryingbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.studio.dryingbutler.R;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.ui
 * file name: AboutActivity
 * creator: WindFromFarEast
 * created time: 2017/9/13 17:14
 * description: 关于界面
 */

public class AboutActivity extends AppCompatActivity
{
    private Button btn_about_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
    }

    private void initView()
    {
        btn_about_back= (Button) findViewById(R.id.btn_about_back);

        btn_about_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
    }
}
