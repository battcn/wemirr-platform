package com.battcn.search.facade;

import com.battcn.framework.commons.entity.PageResult;
import com.battcn.search.pojo.po.Book;

import java.util.List;

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

    /**
     * 根据书籍编号查询书籍信息
     *
     * @param bookNo 书籍编号
     * @return 书籍信息
     */
    Book selectByBookNo(String bookNo);

    /**
     * 查询推荐书籍
     *
     * @param recommend 推荐类型
     * @return 查询结果
     */
    List<Book> findByRecommendOrderByLastModifiedTime(Byte recommend);

}
