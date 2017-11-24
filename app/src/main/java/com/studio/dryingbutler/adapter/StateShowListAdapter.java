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
            viewHolder.tv_state_change= (TextView) convertView.findViewById(R.id.tv_state_change);
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
            viewHolder.tv_state_time.setText(mList.get(position).getTime());
            viewHolder.tv_state_change.setText(mList.get(position).getDesc());
        }
        else
        {
            viewHolder.tv_state_time.setText(mList.get(position).getTime());
        }
        return view;
    }

    class ViewHolder
    {
        private ImageView iv_state_show_point;
        private TextView tv_state_change;
        private TextView tv_state_time;
    }
}
