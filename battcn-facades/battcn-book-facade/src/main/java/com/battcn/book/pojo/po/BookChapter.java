package com.battcn.book.pojo.po;

import com.battcn.framework.mybatis.pojo.RecordEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * @author Levin
 * @author 2018/03/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_book_chapter")
public class BookChapter extends RecordEntity {

    private static final long serialVersionUID = -6179005893626885887L;

    private Integer id;
    private String bookNo;
    private String link;
    private String title;
    private String content;
    private boolean status;

}
