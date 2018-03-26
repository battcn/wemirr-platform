package com.battcn.book.pojo.po;

import com.battcn.framework.mybatis.pojo.RecordEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Levin
 * @since 2018/3/23 0023
 */
@Table(name = "t_book_like")
@EqualsAndHashCode(callSuper = true)
@Data
public class BookLike extends RecordEntity {

    private static final long serialVersionUID = -4334133978678268945L;

    @Id
    private Long id;
    private String bookNo;
    private String memberNo;

}
