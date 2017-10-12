package com.studio.dryingbutler.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.entity.MyAppointment;

import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.adapter
 * file name: MyAppointmentListAdapter
 * creator: WindFromFarEast
 * created time: 2017/9/17 14:02
 * description: 我的预约ListView适配器
 */

public class MyAppointmentListAdapter extends BaseAdapter
{
    //数据源
    private List<MyAppointment> mList;
    //上下文
    private Activity activity;

    public MyAppointmentListAdapter(List<MyAppointment> mList,Activity activity)
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
            view= LayoutInflater.from(activity).inflate(R.layout.item_my_appointment,null);
            convertView=view;
            viewHolder=new ViewHolder();
            viewHolder.tv_my_appointment_model= (TextView) convertView.findViewById(R.id.tv_my_appointment_model);
            viewHolder.tv_my_appointment_number= (TextView) convertView.findViewById(R.id.tv_my_appointment_number);
            viewHolder.tv_my_appointment_type= (TextView) convertView.findViewById(R.id.tv_my_appointment_type);
            convertView.setTag(viewHolder);
        }
        else
        {
            view=convertView;
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_my_appointment_model.setText("设备型号："+""+mList.get(position).getDeviceModel());
        viewHolder.tv_my_appointment_type.setText("烘干种类："+""+mList.get(position).getDryingType());
        viewHolder.tv_my_appointment_number.setText("预约"+(position+1)+"："+mList.get(position).getHandledState());
        return view;
    }

    class ViewHolder
    {
        private TextView tv_my_appointment_number;
        private TextView tv_my_appointment_model;
        private TextView tv_my_appointment_type;
    }
}
