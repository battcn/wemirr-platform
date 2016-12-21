package com.battcn.platform.system.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_sys_logs")
public class LogsEntity implements java.io.Serializable
{

    private static final long serialVersionUID = -1285002170637971804L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String account;
    private String title;
    private String message;
    private Date optime;
    private String ip;
    private String params;
    private String url;
    private Long duration;
    private String methods;

    public LogsEntity()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getAccount()
    {
        return this.account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getMessage()
    {
        return this.message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Date getOptime()
    {
        return this.optime;
    }

    public void setOptime(Date optime)
    {
        this.optime = optime;
    }

    public String getIp()
    {
        return this.ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getParams()
    {
        return params;
    }

    public void setParams(String params)
    {
        this.params = params;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Long getDuration()
    {
        return duration;
    }

    public void setDuration(Long duration)
    {
        this.duration = duration;
    }

    public String getMethods()
    {
        return methods;
    }

    public void setMethods(String methods)
    {
        this.methods = methods;
    }


}