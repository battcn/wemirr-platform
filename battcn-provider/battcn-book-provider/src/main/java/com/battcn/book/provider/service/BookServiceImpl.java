package com.battcn.book.provider.service;

import com.battcn.book.facade.BookService;
import com.battcn.book.pojo.po.Book;
import com.battcn.book.pojo.po.BookType;
import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;

import java.util.List;

@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {

    @Override
    public List<BookType> queryBookTypeForList() {
        return null;
    }
}