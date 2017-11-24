package com.studio.dryingbutler.fragment;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.studio.dryingbutler.R;
import com.studio.dryingbutler.Utils.SharedUtil;
import com.studio.dryingbutler.ui.AutoControlShowActivity;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: AutoControl
 * creator: WindFromFarEast
 * created time: 2017/9/23 12:54
 * description: 自动控制
 */

public class AutoControl extends Fragment
{
    private Spinner spinner_auto_control;
    private ArrayAdapter adapter;
    private List<String> adapterDataSourceList=new ArrayList<>();
    private Button btn_auto_control;
    private EditText et_auto_control;
    private LinearLayout ll_auto_control_input;
    private LinearLayout ll_auto_control_warning;
    private VideoView vv_auto_control;
    private ImageView iv_auto_control;
    private Button btn_auto_control_video;
    private File videoFile1;
    private File videoFile2;
    private File videoFile3;
    private File videoFile4;
    private int flag=0;//用来判断spinner是否被选中的标志位

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_auto_control,null);
        initView(view);
        return view;
    }

    private void initView(View view)
    {
        spinner_auto_control= (Spinner) view.findViewById(R.id.spinner_auto_control);
        btn_auto_control= (Button) view.findViewById(R.id.btn_auto_control);
        et_auto_control= (EditText) view.findViewById(R.id.et_auto_control);
        ll_auto_control_input= (LinearLayout) view.findViewById(R.id.ll_auto_control_input);
        ll_auto_control_warning= (LinearLayout) view.findViewById(R.id.ll_auto_control_warning);
        vv_auto_control= (VideoView) view.findViewById(R.id.vv_auto_control);
        iv_auto_control= (ImageView) view.findViewById(R.id.iv_auto_control);
        btn_auto_control_video= (Button) view.findViewById(R.id.btn_auto_control_video);

        initVideoPath();

        fillAdapterSourceList();
        adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,adapterDataSourceList);
        adapter.setDropDownViewResource(R.layout.my_simple_spinner_dropdown_item);
        spinner_auto_control.setAdapter(adapter);

        spinner_auto_control.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                spinner_auto_control.setBackgroundResource(R.drawable.spinner_bg);
                flag=1;
                SharedUtil.saveStringData("spinner_auto_control",adapterDataSourceList.get(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                spinner_auto_control.setBackgroundResource(R.drawable.spinner_bg);
            }
        });

        btn_auto_control.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (flag==1)
                {
                    if (!(et_auto_control.getText().toString().isEmpty()))
                    {
                        SharedUtil.saveStringData("drying_number", et_auto_control.getText().toString());
                        //接下来是跳转到新的Activity
                        ll_auto_control_input.setVisibility(View.GONE);
                        ll_auto_control_warning.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"请输入烘干量",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity(),"请选择烘干品种",Toast.LENGTH_SHORT).show();
                }
            }
        });

        vv_auto_control.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                //播放完毕后显示图片
                vv_auto_control.setVisibility(View.GONE);
                iv_auto_control.setVisibility(View.VISIBLE);
            }
        });

        iv_auto_control.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //先判断内存中是否有用户选择的品种的视频
                if (SharedUtil.getStringData("spinner_auto_control").equals("核桃"))
                {
                    if (videoFile3.exists())
                    {
                        iv_auto_control.setVisibility(View.GONE);
                        vv_auto_control.setVisibility(View.VISIBLE);
                        vv_auto_control.setVideoPath(videoFile3.getPath());
                        vv_auto_control.start();
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"目标服务器不存在该视频文件!",Toast.LENGTH_SHORT).show();
                    }
                }
                else if (SharedUtil.getStringData("spinner_auto_control").equals("枸杞"))
                {
                    if (videoFile2.exists())
                    {
                        iv_auto_control.setVisibility(View.GONE);
                        vv_auto_control.setVisibility(View.VISIBLE);
                        vv_auto_control.setVideoPath(videoFile2.getPath());
                        vv_auto_control.start();
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"目标服务器不存在该视频文件!",Toast.LENGTH_SHORT).show();
                    }
                }
                else if (SharedUtil.getStringData("spinner_auto_control").equals("大枣"))
                {
                    if (videoFile1.exists())
                    {
                        iv_auto_control.setVisibility(View.GONE);
                        vv_auto_control.setVisibility(View.VISIBLE);
                        vv_auto_control.setVideoPath(videoFile1.getPath());
                        vv_auto_control.start();
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"目标服务器不存在该视频文件!",Toast.LENGTH_SHORT).show();
                    }
                }
                else if (SharedUtil.getStringData("spinner_auto_control").equals("胡萝卜"))
                {
                    if (videoFile1.exists())
                    {
                        iv_auto_control.setVisibility(View.GONE);
                        vv_auto_control.setVisibility(View.VISIBLE);
                        vv_auto_control.setVideoPath(videoFile1.getPath());
                        vv_auto_control.start();
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"目标服务器不存在该视频文件!",Toast.LENGTH_SHORT).show();
                    }
                }
                else if (SharedUtil.getStringData("spinner_auto_control").equals("白菜"))
                {
                    if (videoFile4.exists())
                    {
                        iv_auto_control.setVisibility(View.GONE);
                        vv_auto_control.setVisibility(View.VISIBLE);
                        vv_auto_control.setVideoPath(videoFile4.getPath());
                        vv_auto_control.start();
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"目标服务器不存在该视频文件!",Toast.LENGTH_SHORT).show();
                    }
                }
                else if (SharedUtil.getStringData("spinner_auto_control").equals("烟叶"))
                {
                    if (videoFile4.exists())
                    {
                        iv_auto_control.setVisibility(View.GONE);
                        vv_auto_control.setVisibility(View.VISIBLE);
                        vv_auto_control.setVideoPath(videoFile4.getPath());
                        vv_auto_control.start();
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"目标服务器不存在该视频文件!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_auto_control_video.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ll_auto_control_warning.setVisibility(View.GONE);
                ll_auto_control_input.setVisibility(View.VISIBLE);
                //跳转到新的Activity
                startActivity(new Intent(getActivity(),AutoControlShowActivity.class));
            }
        });
    }

    private void fillAdapterSourceList()
    {
        adapterDataSourceList.add("核桃");
        adapterDataSourceList.add("枸杞");
        adapterDataSourceList.add("大枣");
        adapterDataSourceList.add("胡萝卜");
        adapterDataSourceList.add("白菜");
        adapterDataSourceList.add("烟叶");
    }

    private void initVideoPath()
    {
        videoFile1=new File(Environment.getExternalStorageDirectory(),"大枣烘干机视频 13791687955刘平_标清.mp4");
        videoFile2=new File(Environment.getExternalStorageDirectory(),"枸杞烘干机_标清.mp4");
        videoFile3=new File(Environment.getExternalStorageDirectory(),"核桃烘干房_标清.mp4");
        videoFile4=new File(Environment.getExternalStorageDirectory(),"土豆片烘干机 胡萝卜烘干机 红薯干烘干机 地瓜干烘干机 芒果烘干机 柠檬烘干机 香蕉烘干机 果脯烘干机 草莓烘干机_标清.mp4");
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        if (vv_auto_control!=null)
        {
            vv_auto_control.suspend();
        }
    }
}
