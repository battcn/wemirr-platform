package com.battcn.management.webmagic.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Director {

    private Integer id;

    private String directorName;
    
    private String movies;

    private String movieIds;

    private Date updateTime;
}