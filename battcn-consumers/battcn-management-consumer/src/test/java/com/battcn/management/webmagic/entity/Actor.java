package com.battcn.management.webmagic.entity;

import lombok.Data;

@Data
public class Actor {

    private Integer id;

    private String actorName;

    private String movieIds;

    private String movies;

    private String chineseName;

    private String sex;

    private String homePlace;
}