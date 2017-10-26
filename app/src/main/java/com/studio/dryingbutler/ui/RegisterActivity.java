package com.studio.dryingbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gizwits.gizwifisdk.api.GizWifiSDK;
import com.gizwits.gizwifisdk.enumration.GizUserAccountType;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.gizwits.gizwifisdk.listener.GizWifiSDKListener;
import com.studio.dryingbutler.R;
import com.studio.dryingbutler.Utils.StaticData;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.ui
 * file name: RegisterActivity
 * creator: WindFromFarEast
 * created time: 2017/10/16 16:14
 * description: 用户注册界面
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText et_register_phone;
    private EditText et_register_phone_password;
    private EditText et_register_phone_code;
    private Button btn_get_phone_code;
    private Button btn_phone_register;
    private GizWifiSDKListener gizListener=new GizWifiSDKListener()
    {
        @Override
        public void didRequestSendPhoneSMSCode(GizWifiErrorCode result, String token)
        {
            //发送手机验证码回调
            if (result==GizWifiErrorCode.GIZ_SDK_SUCCESS)
            {
                Toast.makeText(RegisterActivity.this,"验证码发送成功",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(RegisterActivity.this,"验证码发送失败",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void didRegisterUser(GizWifiErrorCode result, String uid, String token)
        {
            if (result==GizWifiErrorCode.GIZ_SDK_SUCCESS)
            {
                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView()
    {
        et_register_phone= (EditText) findViewById(R.id.et_register_phone);
        et_register_phone_code= (EditText) findViewById(R.id.et_register_phone_code);
        et_register_phone_password= (EditText) findViewById(R.id.et_register_phone_password);
        btn_get_phone_code= (Button) findViewById(R.id.btn_get_phone_code);
        btn_phone_register= (Button) findViewById(R.id.btn_phone_register);

        btn_get_phone_code.setOnClickListener(this);
        btn_phone_register.setOnClickListener(this);
        GizWifiSDK.sharedInstance().setListener(gizListener);
    }

    //获取手机号验证码
    private void getPhoneSms(String phoneNumber)
    {
        GizWifiSDK.sharedInstance().requestSendPhoneSMSCode(StaticData.GIZWITS_APP_KEY,phoneNumber);
    }

    //根据得到的手机号验证码注册用户
    private void registerUserBySms(String phoneNumber,String password,String smsCode)
    {
        GizWifiSDK.sharedInstance().registerUser(phoneNumber,password,smsCode, GizUserAccountType.GizUserPhone);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_get_phone_code:
            {
                getPhoneSms(et_register_phone.getText().toString());
                break;
            }
            case R.id.btn_phone_register:
            {
                registerUserBySms(et_register_phone.getText().toString(),et_register_phone_password.getText().toString(),
                        et_register_phone_code.getText().toString());
                break;
            }
        }
    }
}
