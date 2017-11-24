package com.studio.dryingbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.api.GizWifiSDK;
import com.studio.dryingbutler.R;
import com.studio.dryingbutler.Utils.SharedUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.ui
 * file name: AutoControlShowActivity
 * creator: WindFromFarEast
 * created time: 2017/11/23 23:50
 * description: TODO
 */

public class AutoControlShowActivity extends AppCompatActivity
{
    private TextView tv_auto_control_show_first;
    private TextView tv_auto_control_show_second;
    private TextView tv_auto_control_show_third;
    private ImageView iv_auto_control_show_first;
    private ImageView iv_auto_control_show_second;
    private ImageView iv_auto_control_show_third;
    private TextView tv_auto_control_show_name;
    private Timer timer1=new Timer();
    private Timer timer2=new Timer();
    private Timer timer3=new Timer();
    private int flag;
    private List<GizWifiDevice> devices = GizWifiSDK.sharedInstance().getDeviceList();
    private GizWifiDevice gizWifiDevice=devices.get(0);
    private ConcurrentHashMap<String, Object> command = new ConcurrentHashMap<String, Object> ();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_control_show);
        initView();
        setFlag();
        SharedUtil.saveBooleanData("FirstStep",false);
        SharedUtil.saveBooleanData("SecondStep",false);
        SharedUtil.saveBooleanData("ThirdStep",false);
        showAutoControl();
    }

    private void initView()
    {
        tv_auto_control_show_first= (TextView) findViewById(R.id.tv_auto_control_show_first);
        tv_auto_control_show_second= (TextView) findViewById(R.id.tv_auto_control_show_second);
        tv_auto_control_show_third= (TextView) findViewById(R.id.tv_auto_control_show_third);
        tv_auto_control_show_name= (TextView) findViewById(R.id.tv_auto_control_show_name);
        iv_auto_control_show_first= (ImageView) findViewById(R.id.iv_auto_control_show_first);
        iv_auto_control_show_second= (ImageView) findViewById(R.id.iv_auto_control_show_second);
        iv_auto_control_show_third= (ImageView) findViewById(R.id.iv_auto_control_show_third);
    }

    private void setFlag()
    {
        if (SharedUtil.getStringData("spinner_auto_control").equals("核桃"))
        {
            flag=1;
        }
        else if (SharedUtil.getStringData("spinner_auto_control").equals("枸杞")||SharedUtil.getStringData("spinner_auto_control").equals("大枣")
                ||SharedUtil.getStringData("spinner_auto_control").equals("胡萝卜"))
        {
            flag=2;
        }
        else if (SharedUtil.getStringData("spinner_auto_control").equals("白菜")||SharedUtil.getStringData("spinner_auto_control").equals("烟叶"))
        {
            flag=3;
        }
    }

    private void changeLed(int r,int g,int b)
    {
        command.put("LED_R",r);
        command.put("LED_G",g);
        command.put("LED_B",b);
        gizWifiDevice.write(command,3);
    }

    private void changeMotor(int speed)
    {
        command.put("Motor_Speed",speed);
        devices.get(0).write(command,1);
    }

    private void showAutoControl()
    {
        switch (flag)
        {
            case 1:
            {
                tv_auto_control_show_name.setText(SharedUtil.getStringData("spinner_auto_control"));
                tv_auto_control_show_first.setText("第一阶段————入仓排湿");
                tv_auto_control_show_second.setText("第二阶段————定色初烤");
                tv_auto_control_show_third.setText("第三阶段————文火干仁");

                changeLed(254,254,254);
                //每隔40ms改变一次rgb值
                timer1.schedule(new TimerTask()
                {
                    int b1=5;
                    @Override
                    public void run()
                    {
                        int r=1+(int)(Math.random()*253);
                        int g=1+(int)(Math.random()*253);
                        int b=1+(int)(Math.random()*253);
                        b1--;
                        if (b1<0)
                        {
                            SharedUtil.saveBooleanData("FirstStep",true);
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    iv_auto_control_show_first.setImageResource(R.drawable.show_password_yes);
                                }
                            });
                        }
                        else
                        {
                            changeLed(r,g,b);
                        }
                    }
                },0,2000);

                timer2.schedule(new TimerTask()
                {
                    int count=0;
                    @Override
                    public void run()
                    {
                        if (SharedUtil.getBooleanData("FirstStep"))
                        {
                            if (count > 10)
                            {
                                SharedUtil.saveBooleanData("SecondStep",true);
                                runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        iv_auto_control_show_second.setImageResource(R.drawable.show_password_yes);
                                    }
                                });
                            }
                            else
                            {
                                changeMotor(2);
                                count++;
                            }
                        }
                    }
                },12000,2000);

                timer3.schedule(new TimerTask()
                {
                    int count=0;
                    @Override
                    public void run()
                    {
                        int r=1+(int)(Math.random()*253);
                        int g=1+(int)(Math.random()*253);
                        int b=1+(int)(Math.random()*253);
                        int speed=1+(int)(Math.random()*5);
                        if (SharedUtil.getBooleanData("SecondStep"))
                        {
                            if (count<=5)
                            {
                                changeLed(r, g, b);
                                changeMotor(speed);
                                count++;
                            }
                            else
                            {
                                changeMotor(0);
                                SharedUtil.saveBooleanData("FirstStep",false);
                                SharedUtil.saveBooleanData("SecondStep",false);
                                SharedUtil.saveBooleanData("ThirdStep",false);
                                runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        iv_auto_control_show_third.setImageResource(R.drawable.show_password_yes);
                                    }
                                });
                                timer1.cancel();
                                timer3.cancel();
                                timer2.cancel();
                            }
                        }
                    }
                },25000,2000);

                break;
            }
            case 2:
            {
                tv_auto_control_show_name.setText(SharedUtil.getStringData("spinner_auto_control"));
                tv_auto_control_show_first.setText("第一阶段————平衡温度");
                tv_auto_control_show_second.setText("第二阶段————加速排湿");
                tv_auto_control_show_third.setText("第三阶段————恒速排湿");

                changeLed(254,254,254);
                //每隔40ms改变一次rgb值
                timer1.schedule(new TimerTask()
                {
                    int b1=5;
                    @Override
                    public void run()
                    {
                        int r=1+(int)(Math.random()*253);
                        int g=1+(int)(Math.random()*253);
                        int b=1+(int)(Math.random()*253);
                        b1--;
                        if (b1<0)
                        {
                            SharedUtil.saveBooleanData("FirstStep",true);
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    iv_auto_control_show_first.setImageResource(R.drawable.show_password_yes);
                                }
                            });
                        }
                        else
                        {
                            changeLed(r,g,b);
                        }
                    }
                },0,2000);

                timer2.schedule(new TimerTask()
                {
                    int count=0;
                    @Override
                    public void run()
                    {
                        if (SharedUtil.getBooleanData("FirstStep"))
                        {
                            if (count > 10)
                            {
                                SharedUtil.saveBooleanData("SecondStep",true);
                                runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        iv_auto_control_show_second.setImageResource(R.drawable.show_password_yes);
                                    }
                                });
                            }
                            else
                            {
                                changeMotor(2);
                                count++;
                            }
                        }
                    }
                },12000,2000);

                timer3.schedule(new TimerTask()
                {
                    int count=0;
                    @Override
                    public void run()
                    {
                        int r=1+(int)(Math.random()*253);
                        int g=1+(int)(Math.random()*253);
                        int b=1+(int)(Math.random()*253);
                        int speed=1+(int)(Math.random()*5);
                        if (SharedUtil.getBooleanData("SecondStep"))
                        {
                            if (count<=5)
                            {
                                changeLed(r, g, b);
                                changeMotor(speed);
                                count++;
                            }
                            else
                            {
                                changeMotor(0);
                                SharedUtil.saveBooleanData("FirstStep",false);
                                SharedUtil.saveBooleanData("SecondStep",false);
                                SharedUtil.saveBooleanData("ThirdStep",false);
                                runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        iv_auto_control_show_third.setImageResource(R.drawable.show_password_yes);
                                    }
                                });
                                timer1.cancel();
                                timer3.cancel();
                                timer2.cancel();
                            }
                        }
                    }
                },25000,2000);
                break;
            }
            case 3:
            {
                tv_auto_control_show_name.setText(SharedUtil.getStringData("spinner_auto_control"));
                tv_auto_control_show_first.setText("第一阶段————变黄");
                tv_auto_control_show_second.setText("第二阶段————定色");
                tv_auto_control_show_third.setText("第三阶段————干筋");

                changeLed(254,254,254);
                //每隔40ms改变一次rgb值
                timer1.schedule(new TimerTask()
                {
                    int b1=5;
                    @Override
                    public void run()
                    {
                        int r=1+(int)(Math.random()*253);
                        int g=1+(int)(Math.random()*253);
                        int b=1+(int)(Math.random()*253);
                        b1--;
                        if (b1<0)
                        {
                            SharedUtil.saveBooleanData("FirstStep",true);
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    iv_auto_control_show_first.setImageResource(R.drawable.show_password_yes);
                                }
                            });
                        }
                        else
                        {
                            changeLed(r,g,b);
                        }
                    }
                },0,2000);

                timer2.schedule(new TimerTask()
                {
                    int count=0;
                    @Override
                    public void run()
                    {
                        if (SharedUtil.getBooleanData("FirstStep"))
                        {
                            if (count > 10)
                            {
                                SharedUtil.saveBooleanData("SecondStep",true);
                                runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        iv_auto_control_show_second.setImageResource(R.drawable.show_password_yes);
                                    }
                                });
                            }
                            else
                            {
                                changeMotor(2);
                                count++;
                            }
                        }
                    }
                },12000,2000);

                timer3.schedule(new TimerTask()
                {
                    int count=0;
                    @Override
                    public void run()
                    {
                        int r=1+(int)(Math.random()*253);
                        int g=1+(int)(Math.random()*253);
                        int b=1+(int)(Math.random()*253);
                        int speed=1+(int)(Math.random()*5);
                        if (SharedUtil.getBooleanData("SecondStep"))
                        {
                            if (count<=5)
                            {
                                changeLed(r, g, b);
                                changeMotor(speed);
                                count++;
                            }
                            else
                            {
                                changeMotor(0);
                                SharedUtil.saveBooleanData("FirstStep",false);
                                SharedUtil.saveBooleanData("SecondStep",false);
                                SharedUtil.saveBooleanData("ThirdStep",false);
                                runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        iv_auto_control_show_third.setImageResource(R.drawable.show_password_yes);
                                    }
                                });
                                timer1.cancel();
                                timer3.cancel();
                                timer2.cancel();
                            }
                        }
                    }
                },25000,2000);
                break;
            }
        }
    }
}
