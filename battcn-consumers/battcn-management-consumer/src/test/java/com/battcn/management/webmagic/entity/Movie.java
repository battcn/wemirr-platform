package com.battcn.management.webmagic.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Movie {
    private Integer id;

    private String type;

    private String title;

    private Date releaseDate;

    private Float grade;

    private Date updateTime;

    private String content;
}