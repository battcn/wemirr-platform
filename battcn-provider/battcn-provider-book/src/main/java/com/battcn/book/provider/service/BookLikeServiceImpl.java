package com.battcn.book.provider.service;

import com.battcn.book.facade.BookLikeService;
import com.battcn.book.pojo.po.BookLike;
import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;

/**
 * 点赞
 *
 * @author Levin
 * @since 2018/3/23 0023
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        timeout = 5000
)
public class BookLikeServiceImpl extends BaseServiceImpl<BookLike> implements BookLikeService {


}
