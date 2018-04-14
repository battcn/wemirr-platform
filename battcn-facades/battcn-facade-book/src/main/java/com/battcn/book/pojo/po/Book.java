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
    private String name;
    private String source;
    private String type;
    private String author;
    private String description;
    private String cover;
    private Boolean status;
    private Boolean recommend;
}
