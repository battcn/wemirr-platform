package com.battcn.book.provider.service;

import com.battcn.book.facade.BookChapterService;
import com.battcn.book.pojo.po.BookChapter;
import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;


/**
 * @author Levin
 * @since 2018/03/12
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class BookChapterServiceImpl extends BaseServiceImpl<BookChapter> implements BookChapterService {

}