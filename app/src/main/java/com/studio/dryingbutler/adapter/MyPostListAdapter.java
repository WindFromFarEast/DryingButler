package com.studio.dryingbutler.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.entity.MyPost;


import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.adapter
 * file name: MyPostListAdapter
 * creator: WindFromFarEast
 * created time: 2017/9/19 17:13
 * description: 我的已发布的帖子ListView适配器
 */

public class MyPostListAdapter extends BaseAdapter
{
    private List<MyPost> mList;
    private Activity activity;

    public MyPostListAdapter(List<MyPost> mList,Activity activity)
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
            view= LayoutInflater.from(activity).inflate(R.layout.item_my_post,null);
            convertView=view;
            viewHolder=new ViewHolder();
            viewHolder.tv_my_posted_date= (TextView) view.findViewById(R.id.tv_my_posted_date);
            viewHolder.tv_my_posted_page_views= (TextView) view.findViewById(R.id.tv_my_posted_page_views);
            viewHolder.tv_my_posted_title= (TextView) view.findViewById(R.id.tv_my_posted_title);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
            view=convertView;
        }
        viewHolder.tv_my_posted_title.setText(mList.get(position).getTitle());
        viewHolder.tv_my_posted_date.setText(mList.get(position).getMonth()+"-"+mList.get(position).getDay());
        viewHolder.tv_my_posted_page_views.setText(""+mList.get(position).getPageViews());
        return view;
    }

    class ViewHolder
    {
        private TextView tv_my_posted_title;
        private TextView tv_my_posted_date;
        private TextView tv_my_posted_page_views;
    }
}
