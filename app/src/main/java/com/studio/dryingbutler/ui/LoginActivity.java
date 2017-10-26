package com.studio.dryingbutler.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gizwits.gizwifisdk.api.GizWifiSDK;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.gizwits.gizwifisdk.listener.GizWifiSDKListener;
import com.studio.dryingbutler.MainActivity;
import com.studio.dryingbutler.R;
import com.studio.dryingbutler.Utils.SharedUtil;


/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.ui
 * file name: LoginActivity
 * creator: WindFromFarEast
 * created time: 2017/8/31 14:20
 * description: 登录页
 */

public class LoginActivity extends AppCompatActivity implements View.OnFocusChangeListener
{
    private Button btn_login;
    private ImageView iv_login_username;
    private ImageView iv_login_password;
    private EditText et_login_username;
    private EditText et_login_password;
    private TextView tv_register;
    private GizWifiSDKListener gizListener=new GizWifiSDKListener()
    {
        @Override
        public void didUserLogin(GizWifiErrorCode result, String uid, String token)
        {
            if (result==GizWifiErrorCode.GIZ_SDK_SUCCESS)
            {
                Toast.makeText(LoginActivity.this,"登录成功,正在登录中",Toast.LENGTH_SHORT).show();
                //将用户信息缓存
                SharedUtil.saveStringData("phoneNumber",et_login_username.getText().toString());
                SharedUtil.saveStringData("password",et_login_password.getText().toString());
                SharedUtil.saveStringData("token",token);
                SharedUtil.saveStringData("uid",uid);
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
            else
            {
                Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件
        initView();
    }

    private void initView()
    {
        btn_login= (Button) findViewById(R.id.btn_login);
        iv_login_username= (ImageView) findViewById(R.id.iv_login_username);
        iv_login_password= (ImageView) findViewById(R.id.iv_login_password);
        et_login_username= (EditText) findViewById(R.id.et_login_username);
        et_login_password= (EditText) findViewById(R.id.et_login_password);
        tv_register= (TextView) findViewById(R.id.tv_register);
        //监听EditText焦点获取状态
        et_login_username.setOnFocusChangeListener(this);
        et_login_password.setOnFocusChangeListener(this);
        //
        GizWifiSDK.sharedInstance().setListener(gizListener);
        //为登录按钮设置点击事件
        btn_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //登录
                GizWifiSDK.sharedInstance().userLogin(et_login_username.getText().toString(),et_login_password.getText().toString());
            }
        });
        //
        tv_register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));//跳转到注册界面
            }
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus)
    {
        switch (v.getId())
        {
            case R.id.et_login_username:
            {
                if (hasFocus)
                {
                    //获取焦点时,用户图标变蓝
                    iv_login_username.setImageResource(R.drawable.user_icon_input);
                }
                else
                {
                    iv_login_username.setImageResource(R.drawable.user_icon);
                }
                break;
            }
            case R.id.et_login_password:
            {
                if (hasFocus)
                {
                    iv_login_password.setImageResource(R.drawable.password_icon_input);
                }
                else
                {
                    iv_login_password.setImageResource(R.drawable.password_icon);
                }
                break;
            }
        }
    }
}
