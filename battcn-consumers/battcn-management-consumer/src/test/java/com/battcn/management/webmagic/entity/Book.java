package com.battcn.management.webmagic.entity;


import com.battcn.management.webmagic.BookLabel;
import lombok.Data;

import java.util.List;

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
