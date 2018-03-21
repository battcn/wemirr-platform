package com.battcn.book.pojo.po;

import lombok.Data;

/**
 * @author Levin
 * @since 2018/3/21 0021
 */
@Data
public class BookType implements java.io.Serializable {

    private static final long serialVersionUID = -3577782190937802105L;

    private String type;
    private Integer count;

}
