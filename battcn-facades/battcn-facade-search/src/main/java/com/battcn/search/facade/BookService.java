package com.battcn.search.facade;

import com.battcn.search.pojo.po.Book;
import org.springframework.data.domain.Page;

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
    Page<Book> searchBook(String searchContent);

    Book selectByBookNo(String bookNo);

}
