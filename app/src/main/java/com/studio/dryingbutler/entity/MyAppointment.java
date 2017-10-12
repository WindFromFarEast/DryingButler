package com.studio.dryingbutler.entity;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.entity
 * file name: MyAppointment
 * creator: WindFromFarEast
 * created time: 2017/9/17 14:02
 * description: 我的预约实体类
 */

public class MyAppointment
{
    private String deviceModel;//设备型号
    private String dryingType;//烘干种类
    private String handledState;//处理状态

    public MyAppointment()
    {

    }

    public String getHandledState()
    {
        return handledState;
    }

    public void setHandledState(String handledState)
    {
        this.handledState = handledState;
    }

    public MyAppointment(String deviceModel, String dryingType,String handledState)
    {
        setDeviceModel(deviceModel);
        setDryingType(dryingType);
        setHandledState(handledState);
    }

    public String getDeviceModel()
    {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel)
    {
        this.deviceModel = deviceModel;
    }

    public String getDryingType()
    {
        return dryingType;
    }

    public void setDryingType(String dryingType)
    {
        this.dryingType = dryingType;
    }
}
