package com.studio.dryingbutler.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.application.MyApplication;
import com.studio.dryingbutler.entity.Device;
import com.studio.dryingbutler.fragment.Monitor;
import com.studio.dryingbutler.ui.MonitorActivity;

import java.util.List;


/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.adapter
 * file name: MonitorDeviceAdapter
 * creator: WindFromFarEast
 * created time: 2017/9/22 15:01
 * description: 监控界面设备列表适配器
 */

public class MonitorDeviceAdapter extends BaseAdapter
{
    private List<Device> mList;
    private Activity activity;

    public MonitorDeviceAdapter(List<Device> mList,Activity activity)
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
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View view;
        ViewHolder viewHolder;
        if (convertView==null)
        {
            view= LayoutInflater.from(activity).inflate(R.layout.item_monitor_device,null);
            convertView=view;
            viewHolder=new ViewHolder();
            viewHolder.rl_monitor_device= (RelativeLayout) convertView.findViewById(R.id.rl_monitor_device);
            viewHolder.tv_monitor_device_name= (TextView) convertView.findViewById(R.id.tv_monitor_device_name);
            viewHolder.tv_monitor_device_state= (TextView) convertView.findViewById(R.id.tv_monitor_device_state);
            convertView.setTag(viewHolder);
        }
        else
        {
            view=convertView;
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if (mList.get(position).isWork())
        {
            viewHolder.rl_monitor_device.setBackgroundResource(R.drawable.rectangle6);
            viewHolder.tv_monitor_device_name.setText(mList.get(position).getName());
            viewHolder.tv_monitor_device_state.setText("已开启");
            viewHolder.rl_monitor_device.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent=new Intent(MyApplication.getContext(), MonitorActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("deviceName",mList.get(position).getName());
                    MyApplication.getContext().startActivity(intent);
                }
            });
        }
        else
        {
            viewHolder.rl_monitor_device.setBackgroundResource(R.drawable.rectangle7);
            viewHolder.tv_monitor_device_name.setText(mList.get(position).getName());
            viewHolder.tv_monitor_device_state.setText("未开启");
            viewHolder.rl_monitor_device.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(MyApplication.getContext(),"设备"+mList.get(position).getName()+"不在线",Toast.LENGTH_SHORT).show();
                }
            });
        }
        return view;
    }

    class ViewHolder
    {
        private RelativeLayout rl_monitor_device;
        private TextView tv_monitor_device_name;
        private TextView tv_monitor_device_state;
    }
}
