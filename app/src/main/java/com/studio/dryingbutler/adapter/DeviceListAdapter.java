package com.studio.dryingbutler.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.entity.Device;

import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.adapter
 * file name: DeviceListAdapter
 * creator: WindFromFarEast
 * created time: 2017/9/9 13:35
 * description: 设备信息列表适配器
 */

public class DeviceListAdapter extends BaseAdapter
{
    private List<Device> mList;
    private Activity activity;

    public DeviceListAdapter(List<Device> mList,Activity activity)
    {
        this.mList=mList;
        this.activity=activity;
    }

    @Override
    public int getCount()
    {
        return mList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view;
        final ViewHolder viewHolder;
        if (convertView==null)
        {
            view=LayoutInflater.from(activity).inflate(R.layout.device_item,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_home_device_name= (TextView) view.findViewById(R.id.tv_home_device_name);
            viewHolder.tv_home_device_work= (TextView) view.findViewById(R.id.tv_home_device_work);
            viewHolder.tv_home_device_fan= (TextView) view.findViewById(R.id.tv_home_device_fan);
            viewHolder.tv_home_device_battery= (TextView) view.findViewById(R.id.tv_home_device_battery);
            viewHolder.tv_home_device_cir_fan= (TextView) view.findViewById(R.id.tv_home_device_cir_fan);
            viewHolder.tv_home_device_auxiliary_fan= (TextView) view.findViewById(R.id.tv_home_device_auxiliary_fan);
            viewHolder.tv_home_device_cool1= (TextView) view.findViewById(R.id.tv_home_device_cool1);
            viewHolder.tv_home_device_cool2= (TextView) view.findViewById(R.id.tv_home_device_cool2);
            viewHolder.tv_home_device_blower= (TextView) view.findViewById(R.id.tv_home_device_blower);
            viewHolder.tv_home_device_fire= (TextView) view.findViewById(R.id.tv_home_device_fire);
            viewHolder.tv_home_device_valve1= (TextView) view.findViewById(R.id.tv_home_device_valve1);
            viewHolder.tv_home_device_valve2= (TextView) view.findViewById(R.id.tv_home_device_valve2);
            viewHolder.ll_home_device_desc= (LinearLayout) view.findViewById(R.id.ll_home_device_desc);
            viewHolder.btn_home_device_desc= (Button) view.findViewById(R.id.btn_home_device_desc);
            view.setTag(viewHolder);
        }
        else
        {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_home_device_name.setText("设备"+mList.get(position).getName());
        if (mList.get(position).isWork())
        {
            viewHolder.tv_home_device_work.setText("正常工作，工作进程"+mList.get(position).getProcess()+"%");
        }
        else
        {
            viewHolder.tv_home_device_work.setText("工作停止");
        }

        if (mList.get(position).isFanWork())
        {
            viewHolder.tv_home_device_fan.setText("风机正常工作，无报警");
        }
        else
        {
            viewHolder.tv_home_device_fan.setText("风机停止工作");
        }

        if (mList.get(position).isBatteryWork())
        {
            viewHolder.tv_home_device_battery.setText("电源正常工作，无报警");
        }
        else
        {
            viewHolder.tv_home_device_battery.setText("电源停止工作");
        }
        if (mList.get(position).isCirFanOn())
        {
            viewHolder.tv_home_device_cir_fan.setText("循环风机  开启");
        }
        else
        {
            viewHolder.tv_home_device_cir_fan.setText("循环风机  关闭");
        }

        if (mList.get(position).isBlowerOn())
        {
            viewHolder.tv_home_device_blower.setText("鼓风机  开启");
        }
        else
        {
            viewHolder.tv_home_device_blower.setText("鼓风机  关闭");
        }

        if (mList.get(position).isAxuFanOn())
        {
            viewHolder.tv_home_device_auxiliary_fan.setText("辅助风机  开启");
        }
        else
        {
            viewHolder.tv_home_device_auxiliary_fan.setText("辅助风机  关闭");
        }

        if (mList.get(position).isFire())
        {
            viewHolder.tv_home_device_fire.setText("点火  开启");
        }
        else
        {
            viewHolder.tv_home_device_fire.setText("点火  关闭");
        }

        if (mList.get(position).isCoolOneOn())
        {
            viewHolder.tv_home_device_cool1.setText("冷风门1  开启");
        }
        else
        {
            viewHolder.tv_home_device_cool1.setText("冷风门1  关闭");
        }

        if (mList.get(position).isCoolTwoOn())
        {
            viewHolder.tv_home_device_cool2.setText("冷风门2  开启");
        }
        else
        {
            viewHolder.tv_home_device_cool2.setText("冷风门2  关闭");
        }

        if (mList.get(position).isValveOneOn())
        {
            viewHolder.tv_home_device_valve1.setText("电磁阀1  开启");
        }
        else
        {
            viewHolder.tv_home_device_valve1.setText("电磁阀1  关闭");
        }

        if (mList.get(position).isValveTwoOn())
        {
            viewHolder.tv_home_device_valve2.setText("电磁阀2  开启");
        }
        else
        {
            viewHolder.tv_home_device_valve2.setText("电磁阀2  关闭");
        }
        viewHolder.btn_home_device_desc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (viewHolder.ll_home_device_desc.getVisibility()==View.GONE)
                {
                    viewHolder.ll_home_device_desc.setVisibility(View.VISIBLE);
                    viewHolder.btn_home_device_desc.setBackgroundResource(R.drawable.fold);
                }
                else
                {
                    viewHolder.ll_home_device_desc.setVisibility(View.GONE);
                    viewHolder.btn_home_device_desc.setBackgroundResource(R.drawable.unfold);
                }
            }
        });
        return view;
    }

    class ViewHolder
    {
        private TextView tv_home_device_name;
        private TextView tv_home_device_work;
        private TextView tv_home_device_fan;
        private TextView tv_home_device_battery;
        private TextView tv_home_device_cir_fan;
        private TextView tv_home_device_auxiliary_fan;
        private TextView tv_home_device_cool1;
        private TextView tv_home_device_cool2;
        private TextView tv_home_device_blower;
        private TextView tv_home_device_fire;
        private TextView tv_home_device_valve1;
        private TextView tv_home_device_valve2;
        private LinearLayout ll_home_device_desc;
        private Button btn_home_device_desc;
    }
}
