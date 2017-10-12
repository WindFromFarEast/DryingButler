package com.studio.dryingbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.studio.dryingbutler.R;


/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.ui
 * file name: PasswordActivity
 * creator: WindFromFarEast
 * created time: 2017/9/13 16:05
 * description: 密码修改页面
 */

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btn_password_back;
    private EditText et_old_password;
    private EditText et_new_password;
    private Button btn_password_change;
    private CheckBox cb_show_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        initView();
    }

    private void initView()
    {
        btn_password_back= (Button) findViewById(R.id.btn_password_back);
        et_old_password= (EditText) findViewById(R.id.et_old_password);
        et_new_password= (EditText) findViewById(R.id.et_new_password);
        btn_password_change= (Button) findViewById(R.id.btn_password_change);
        cb_show_password= (CheckBox) findViewById(R.id.cb_show_password);

        btn_password_back.setOnClickListener(this);
        btn_password_change.setOnClickListener(this);
        cb_show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    et_old_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    et_new_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else
                {
                    et_old_password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    et_new_password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_password_back:
            {
                onBackPressed();
                break;
            }
            case R.id.btn_password_change:
            {
                String old_password,new_password;
                old_password=et_old_password.getText().toString();
                new_password=et_new_password.getText().toString();
                if ((!TextUtils.isEmpty(old_password))&(!TextUtils.isEmpty(new_password)))
                {
                    if (old_password.equals(new_password))
                    {
                        Toast.makeText(this, "新旧密码不允许相同", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
