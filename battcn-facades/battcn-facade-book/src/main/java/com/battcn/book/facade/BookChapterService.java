package com.battcn.book.facade;


import com.battcn.book.pojo.po.BookChapter;
import com.battcn.framework.mybatis.service.BaseService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Levin
 * @since 2018/03/08
 */
public interface BookChapterService extends BaseService<BookChapter> {

    /**
     * 根据书籍编号查询章节信息
     *
     * @param bookNo 书籍编号
     * @return 章节信息
     */
    List<BookChapter> queryChapterForList(@Param("bookNo") String bookNo);


}
