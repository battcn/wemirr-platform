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
@Table(name = "t_book_chapter")
public class BookChapter extends RecordEntity {

    private static final long serialVersionUID = -6179005893626885887L;

    @Id
    private Long id;
    private String bookNo;
    private String source;
    private String target;
    private String title;
    private String content;
    private Boolean status;

    public BookChapter(String source) {
        this.source = source;
    }
}
