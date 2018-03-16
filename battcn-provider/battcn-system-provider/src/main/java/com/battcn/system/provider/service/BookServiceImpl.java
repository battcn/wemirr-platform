package com.battcn.system.provider.service;

import com.battcn.book.facade.BookService;
import com.battcn.book.pojo.po.Book;
import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;

@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {

}