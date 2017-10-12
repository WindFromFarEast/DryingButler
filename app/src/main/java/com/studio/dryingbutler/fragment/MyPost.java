package com.studio.dryingbutler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.adapter.MyPostListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: MyPost
 * creator: WindFromFarEast
 * created time: 2017/9/18 16:51
 * description: 我的帖子
 */

public class MyPost extends Fragment implements View.OnClickListener
{
    private EditText et_my_post_title;
    private EditText et_my_post_content;
    private Button btn_my_post_submit;
    private LinearLayout ll_my_posted;
    private ListView lv_my_posted;
    private List<com.studio.dryingbutler.entity.MyPost> mList=new ArrayList<>();
    private MyPostListAdapter adapter;
    private ImageView iv_my_posted_fold;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_my_post,null);
        initView(view);
        return view;
    }

    private void initView(View view)
    {
        et_my_post_content= (EditText) view.findViewById(R.id.et_my_post_content);
        et_my_post_title= (EditText) view.findViewById(R.id.et_my_post_title);
        btn_my_post_submit= (Button) view.findViewById(R.id.btn_my_post_submit);
        ll_my_posted= (LinearLayout) view.findViewById(R.id.ll_my_posted);
        lv_my_posted= (ListView) view.findViewById(R.id.lv_my_posted);
        iv_my_posted_fold= (ImageView) view.findViewById(R.id.iv_my_posted_fold);

        adapter=new MyPostListAdapter(mList,getActivity());
        lv_my_posted.setAdapter(adapter);

        btn_my_post_submit.setOnClickListener(this);
        ll_my_posted.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_my_post_submit:
            {
                createMyPost();
                adapter.notifyDataSetChanged();
                et_my_post_title.setText("");
                et_my_post_content.setText("");
                Toast.makeText(getActivity(),"提交成功",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.ll_my_posted:
            {
                if (lv_my_posted.getVisibility()==View.GONE)
                {
                    lv_my_posted.setVisibility(View.VISIBLE);
                    iv_my_posted_fold.setImageResource(R.drawable.btn_my_posted_pushed);
                }
                else
                {
                    lv_my_posted.setVisibility(View.GONE);
                    iv_my_posted_fold.setImageResource(R.drawable.btn_my_posted);
                }
                break;
            }
        }
    }

    private void createMyPost()
    {
        String title=et_my_post_title.getText().toString();
        String content=et_my_post_content.getText().toString();
        com.studio.dryingbutler.entity.MyPost myPost=new com.studio.dryingbutler.entity.MyPost(title,content,0,0);
        myPost.setMonth(Calendar.getInstance().get(Calendar.MONTH)+1);
        myPost.setDay(Calendar.getInstance().get(Calendar.DATE));
        mList.add(myPost);
    }
}
