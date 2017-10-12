package com.studio.dryingbutler.entity;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.entity
 * file name: Device
 * creator: WindFromFarEast
 * created time: 2017/9/9 13:36
 * description: 设备实体类
 */

public class Device
{
    private String name;
    private boolean isWork;
    private boolean isFanWork;
    private boolean isBatteryWork;
    private boolean isCirFanOn;
    private boolean isBlowerOn;
    private boolean isAxuFanOn;
    private boolean isFire;
    private boolean isCoolOneOn;
    private boolean isCoolTwoOn;
    private boolean isValveOneOn;
    private boolean isValveTwoOn;
    private int temperature;
    private int humidity;
    private int process;
    private String time;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isWork()
    {
        return isWork;
    }

    public void setWork(boolean work)
    {
        isWork = work;
    }

    public boolean isFanWork()
    {
        return isFanWork;
    }

    public void setFanWork(boolean fanWork)
    {
        isFanWork = fanWork;
    }

    public boolean isBatteryWork()
    {
        return isBatteryWork;
    }

    public void setBatteryWork(boolean batteryWork)
    {
        isBatteryWork = batteryWork;
    }

    public boolean isCirFanOn()
    {
        return isCirFanOn;
    }

    public void setCirFanOn(boolean cirFanOn)
    {
        isCirFanOn = cirFanOn;
    }

    public boolean isBlowerOn()
    {
        return isBlowerOn;
    }

    public void setBlowerOn(boolean blowerOn)
    {
        isBlowerOn = blowerOn;
    }

    public boolean isAxuFanOn()
    {
        return isAxuFanOn;
    }

    public void setAxuFanOn(boolean axuFanOn)
    {
        isAxuFanOn = axuFanOn;
    }

    public boolean isFire()
    {
        return isFire;
    }

    public void setFire(boolean fire)
    {
        isFire = fire;
    }

    public boolean isCoolOneOn()
    {
        return isCoolOneOn;
    }

    public void setCoolOneOn(boolean coolOneOn)
    {
        isCoolOneOn = coolOneOn;
    }

    public boolean isCoolTwoOn()
    {
        return isCoolTwoOn;
    }

    public void setCoolTwoOn(boolean coolTwoOn)
    {
        isCoolTwoOn = coolTwoOn;
    }

    public boolean isValveOneOn()
    {
        return isValveOneOn;
    }

    public void setValveOneOn(boolean valveOneOn)
    {
        isValveOneOn = valveOneOn;
    }

    public boolean isValveTwoOn()
    {
        return isValveTwoOn;
    }

    public void setValveTwoOn(boolean valveTwoOn)
    {
        isValveTwoOn = valveTwoOn;
    }

    public int getTemperature()
    {
        return temperature;
    }

    public void setTemperature(int temperature)
    {
        this.temperature = temperature;
    }

    public int getHumidity()
    {
        return humidity;
    }

    public void setHumidity(int humidity)
    {
        this.humidity = humidity;
    }

    public int getProcess()
    {
        return process;
    }

    public void setProcess(int process)
    {
        this.process = process;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
}
