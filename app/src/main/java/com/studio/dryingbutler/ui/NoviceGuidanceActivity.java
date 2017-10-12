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
 * file name: NoviceGuidanceActivity
 * creator: WindFromFarEast
 * created time: 2017/9/15 16:10
 * description: 新手指导页面
 */

public class NoviceGuidanceActivity extends AppCompatActivity
{
    private Button btn_novice_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_novice);
        initView();
    }

    private void initView()
    {
        btn_novice_back= (Button) findViewById(R.id.btn_novice_back);
        btn_novice_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
    }
}
