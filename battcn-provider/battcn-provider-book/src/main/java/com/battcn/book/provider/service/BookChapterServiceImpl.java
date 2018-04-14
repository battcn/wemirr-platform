package com.battcn.book.provider.service;

import com.battcn.book.facade.BookChapterService;
import com.battcn.book.pojo.po.BookChapter;
import com.battcn.book.provider.mapper.BookChapterMapper;
import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @author Levin
 * @since 2018/03/12
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        timeout = 5000
)
public class BookChapterServiceImpl extends BaseServiceImpl<BookChapter> implements BookChapterService {

    private final BookChapterMapper bookChapterMapper;

    @Autowired
    public BookChapterServiceImpl(BookChapterMapper bookChapterMapper) {
        this.bookChapterMapper = bookChapterMapper;
    }

    @Override
    public List<BookChapter> queryChapterForList(String bookNo) {
        return this.bookChapterMapper.selectByBookNo(bookNo);
    }
}