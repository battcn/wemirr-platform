package com.battcn.management.webmagic.entity;


import java.util.List;

import com.battcn.management.webmagic.BookLabel;

import lombok.Data;

/**
 * @author Levin
 * @since 2018/03/09
 */
@Data
public class Book {

    private String name;
    private String link;
    private String type;
    private String author;
    private String info;
    private String cover;
    private List<BookLabel> labels;

}
