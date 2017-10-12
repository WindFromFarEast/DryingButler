package com.studio.dryingbutler.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.entity.Device;


import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.adapter
 * file name: StateShowListAdapter
 * creator: WindFromFarEast
 * created time: 2017/9/21 15:21
 * description: 设备状态显示列表适配器
 */

public class StateShowListAdapter extends BaseAdapter
{
    private List<Device> mList;
    private Activity activity;

    public StateShowListAdapter(List<Device> mList, Activity activity)
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
        ViewHolder viewHolder;
        if (convertView==null)
        {
            view= LayoutInflater.from(activity).inflate(R.layout.item_state_show,null);
            convertView=view;
            viewHolder=new ViewHolder();
            viewHolder.iv_state_show_point= (ImageView) convertView.findViewById(R.id.iv_state_show_point);
            viewHolder.tv_state_work= (TextView) convertView.findViewById(R.id.tv_state_work);
            viewHolder.tv_state_fan= (TextView) convertView.findViewById(R.id.tv_state_fan);
            viewHolder.tv_state_cold= (TextView) convertView.findViewById(R.id.tv_state_cold);
            viewHolder.tv_state_valve= (TextView) convertView.findViewById(R.id.tv_state_valve);
            viewHolder.tv_state_fire= (TextView) convertView.findViewById(R.id.tv_state_fire);
            viewHolder.tv_state_time= (TextView) convertView.findViewById(R.id.tv_state_time);
            convertView.setTag(viewHolder);
        }
        else
        {
            view=convertView;
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if (mList.get(position).isWork())
        {
            viewHolder.tv_state_work.setText("正常工作，工作进程："+mList.get(position).getProcess()+"%");
            String cirFanState,axuFanState;
            cirFanState=mList.get(position).isCirFanOn()?"开启":"关闭";
            axuFanState=mList.get(position).isAxuFanOn()?"开启":"关闭";
            viewHolder.tv_state_fan.setText("循环风机："+cirFanState+"，辅助风机："+axuFanState);
            String coolState1,coolState2;
            coolState1=mList.get(position).isCoolOneOn()?"开启":"关闭";
            coolState2=mList.get(position).isCoolTwoOn()?"开启":"关闭";
            viewHolder.tv_state_cold.setText("冷风门1："+coolState1+"，冷风门2："+coolState2);
            String valveState1,valveState2;
            valveState1=mList.get(position).isValveOneOn()?"开启":"关闭";
            valveState2=mList.get(position).isValveTwoOn()?"开启":"关闭";
            viewHolder.tv_state_valve.setText("电磁阀1："+valveState1+"，电磁阀2："+valveState2);
            String blowerState,fireState;
            blowerState=mList.get(position).isBlowerOn()?"开启":"关闭";
            fireState=mList.get(position).isFire()?"开启":"关闭";
            viewHolder.tv_state_fire.setText("鼓风机："+blowerState+"，点火："+fireState);
            viewHolder.tv_state_time.setText(mList.get(position).getTime());
        }
        else
        {
            viewHolder.tv_state_work.setText("未工作");
            viewHolder.tv_state_fan.setText("");
            viewHolder.tv_state_cold.setText("");
            viewHolder.tv_state_valve.setText("");
            viewHolder.tv_state_fire.setText("");
            viewHolder.tv_state_time.setText(mList.get(position).getTime());
        }
        return view;
    }

    class ViewHolder
    {
        private ImageView iv_state_show_point;
        private TextView tv_state_work;
        private TextView tv_state_fan;
        private TextView tv_state_cold;
        private TextView tv_state_valve;
        private TextView tv_state_fire;
        private TextView tv_state_time;
    }
}
