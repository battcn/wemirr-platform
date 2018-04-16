package com.battcn.book.pojo.po;

import com.battcn.framework.mybatis.pojo.RecordEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Levin
 * @author 2018/03/12
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_book")
public class Book extends RecordEntity {

    private static final long serialVersionUID = -6179005893626885887L;

    @Id
    private String bookNo;
    /**
     * 书籍名称
     */
    private String name;
    /**
     * 书籍来源
     */
    private String source;
    private String bookType;
    private String author;
    /**
     * 描述（简介）
     */
    private String description;
    /**
     * 书籍封面
     */
    private String cover;
    /**
     * 标签
     */
    private String tags;
    /**
     * 是否完结 0=未完结 1=已完结
     */
    private Boolean status;
    /**
     * 0=普通书本 1=推荐 2=热门
     */
    private Byte recommend;
}
