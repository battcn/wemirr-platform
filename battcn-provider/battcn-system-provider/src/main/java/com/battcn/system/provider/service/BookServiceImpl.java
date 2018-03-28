package com.battcn.system.provider.service;

import com.battcn.book.facade.BookService;
import com.battcn.book.pojo.po.Book;
import com.battcn.book.pojo.po.BookType;
import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;
import com.battcn.system.provider.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Levin
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        timeout = 5000
)
public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {

    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookType> queryBookTypeForList() {
        return this.bookMapper.queryBookTypeForList();
    }
}