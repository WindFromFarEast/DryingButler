package com.studio.dryingbutler.entity;

/**
 * project name: DryingButler
 * package name: com.studio.dryingbutler.entity
 * file name: MyPost
 * creator: WindFromFarEast
 * created time: 2017/9/19 17:05
 * description: 我的帖子实体类
 */

public class MyPost
{
    private String title;
    private String content;
    private int pageViews;
    private int replys;
    private int month;
    private int day;

    public MyPost(String title,String content,int pageViews,int replys)
    {
        setTitle(title);
        setContent(content);
        setPageViews(pageViews);
        setReplys(replys);
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getPageViews()
    {
        return pageViews;
    }

    public void setPageViews(int pageViews)
    {
        this.pageViews = pageViews;
    }

    public int getReplys()
    {
        return replys;
    }

    public void setReplys(int replys)
    {
        this.replys = replys;
    }

    public int getMonth()
    {
        return month;
    }

    public void setMonth(int month)
    {
        this.month = month;
    }

    public int getDay()
    {
        return day;
    }

    public void setDay(int day)
    {
        this.day = day;
    }
}
