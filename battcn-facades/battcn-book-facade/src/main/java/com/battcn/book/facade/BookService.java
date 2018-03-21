package com.battcn.book.facade;

import com.battcn.book.pojo.po.Book;
import com.battcn.book.pojo.po.BookType;
import com.battcn.framework.mybatis.service.BaseService;

import java.util.List;

/**
 * @author Levin
 * @since 2018/03/08
 */
public interface BookService extends BaseService<Book> {

    /**
     * 分组查询书籍类型
     *
     * @return 书籍类型结果集
     */
    List<BookType> queryBookTypeForList();

}
