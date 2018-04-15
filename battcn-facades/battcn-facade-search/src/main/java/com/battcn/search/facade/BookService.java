package com.battcn.search.facade;

import com.battcn.framework.commons.entity.PageResult;
import com.battcn.search.pojo.po.Book;

/**
 * @author Levin
 * @since 2018/03/08
 */
public interface BookService {

    /**
     * 分页查询
     *
     * @param searchContent 检索内容
     * @return 检索的结果
     */
    PageResult<Book> searchBook(String searchContent);

}
