package com.studio.dryingbutler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.studio.dryingbutler.R;

import java.util.ArrayList;
import java.util.List;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.fragment
 * file name: RealTimeMonitor
 * creator: WindFromFarEast
 * created time: 2017/9/23 12:50
 * description: 实时监控
 */

public class RealTimeMonitor extends Fragment implements OnChartGestureListener
{
    private LineChart lc_monitor_temp;
    private LineChart lc_monitor_humidity;
    //图表数据源
    private List<Entry> tempChartEntryList=new ArrayList<>();
    private List<Entry> humidityChartEntryList=new ArrayList<>();
    //图表DataSet
    private LineDataSet tempDataSet=new LineDataSet(tempChartEntryList,"Temp");
    private LineDataSet humidityDataSet=new LineDataSet(humidityChartEntryList,"Humidity");
    //图表Data
    private LineData tempData=new LineData(tempDataSet);
    private LineData humidityData=new LineData(humidityDataSet);
    //图表数值格式化
    private TempValueFormatter tempValueFormatter=new TempValueFormatter();
    private HumidityValueFormatter humidityValueFormatter=new HumidityValueFormatter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_real_time_monitor,null);
        initView(view);
        insertDataToTempChart(1,2);
        insertDataToTempChart(2,3);
        insertDataToTempChart(3,5);
        insertDataToTempChart(4,3);
        insertDataToTempChart(5,7);
        insertDataToTempChart(6,4);
        insertDataToHumidityChart(1,2);
        insertDataToHumidityChart(2,3);
        insertDataToHumidityChart(3,5);
        insertDataToHumidityChart(4,3);
        insertDataToHumidityChart(5,7);
        insertDataToHumidityChart(6,4);
        return view;
    }

    private void initView(View view)
    {
        lc_monitor_temp= (LineChart) view.findViewById(R.id.lc_monitor_temp);
        lc_monitor_humidity= (LineChart) view.findViewById(R.id.lc_monitor_humidity);
        setChartAttr();
    }

    private void setChartAttr()
    {
        //设置温度图表和湿度图表的一些属性
        lc_monitor_temp.setOnChartGestureListener(this);
        lc_monitor_temp.setDrawGridBackground(false);
        lc_monitor_temp.getDescription().setEnabled(false);
        lc_monitor_temp.setTouchEnabled(true);
        lc_monitor_temp.setDragEnabled(true);
        lc_monitor_temp.setScaleEnabled(true);
        lc_monitor_temp.setPinchZoom(true);
        lc_monitor_humidity.setOnChartGestureListener(this);
        lc_monitor_humidity.setDrawGridBackground(false);
        lc_monitor_humidity.getDescription().setEnabled(false);
        lc_monitor_humidity.setTouchEnabled(true);
        lc_monitor_humidity.setDragEnabled(true);
        lc_monitor_humidity.setScaleEnabled(true);
        lc_monitor_humidity.setPinchZoom(true);
        //X轴显示在底部
        lc_monitor_temp.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lc_monitor_humidity.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        //
        lc_monitor_temp.getXAxis().setLabelCount(6);
        lc_monitor_humidity.getXAxis().setLabelCount(6);
        //隐藏Y轴
        lc_monitor_humidity.getAxisLeft().setEnabled(false);
        lc_monitor_humidity.getAxisRight().setEnabled(false);
        lc_monitor_temp.getAxisRight().setEnabled(false);
        lc_monitor_temp.getAxisLeft().setEnabled(false);
        //隐藏图例
        lc_monitor_temp.getLegend().setEnabled(false);
        lc_monitor_humidity.getLegend().setEnabled(false);
        //不显示网格
        lc_monitor_humidity.getXAxis().setDrawGridLines(false);
        lc_monitor_temp.getXAxis().setDrawGridLines(false);
        //设置为曲线
        tempDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        humidityDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //设置曲线粗细
        tempDataSet.setLineWidth(2.5f);
        humidityDataSet.setLineWidth(2.5f);
        //设置坐标点显示数值文字大小
        tempDataSet.setValueTextSize(15);
        humidityDataSet.setValueTextSize(15);
        //格式化坐标点显示数值
        tempDataSet.setValueFormatter(tempValueFormatter);
        humidityDataSet.setValueFormatter(humidityValueFormatter);
        //设置圆点大小
        tempDataSet.setCircleSize(4.5f);
        humidityDataSet.setCircleSize(4.5f);
        //为图表添加数据源
        lc_monitor_temp.setData(tempData);
        lc_monitor_humidity.setData(humidityData);
    }

    //向图表插入新数据点
    private void insertDataToTempChart(float x, float y)
    {
        tempDataSet.addEntry(new Entry(x,y));
        tempData.notifyDataChanged();
        lc_monitor_temp.notifyDataSetChanged();
        lc_monitor_temp.invalidate();
    }

    //向图表插入新数据点
    private void insertDataToHumidityChart(float x, float y)
    {
        humidityDataSet.addEntry(new Entry(x,y));
        humidityData.notifyDataChanged();
        lc_monitor_humidity.notifyDataSetChanged();
        lc_monitor_humidity.invalidate();
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture)
    {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture)
    {

    }

    @Override
    public void onChartLongPressed(MotionEvent me)
    {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me)
    {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me)
    {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY)
    {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY)
    {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY)
    {

    }

    //坐标顶点数据格式化内部类
    class TempValueFormatter implements IValueFormatter
    {
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler)
        {
            return value+"℃";
        }
    }

    //坐标顶点数据格式化内部类
    class HumidityValueFormatter implements IValueFormatter
    {
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler)
        {
            return value+"%";
        }
    }
}
