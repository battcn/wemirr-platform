package com.battcn.search.provider.service;


import com.battcn.framework.commons.entity.PageResult;
import com.battcn.search.facade.BookService;
import com.battcn.search.pojo.po.Book;
import com.battcn.search.provider.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        timeout = 5000
)
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /**
     * 分页参数 -> TODO 代码可迁移到具体项目的公共 common 模块
     */
    private static final Integer PAGE_NUMBER = 0;
    private static final Integer PAGE_SIZE = 10;

    @Override
    public PageResult<Book> searchBook(String searchContent) {
        Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE, Sort.by(new Sort.Order(Sort.Direction.DESC, "createTime")));
        final MatchPhraseQueryBuilder name = QueryBuilders.matchPhraseQuery("name", searchContent).boost(3.0F);
        final MatchPhraseQueryBuilder author = QueryBuilders.matchPhraseQuery("author", searchContent).boost(2.0F);
        final MatchPhraseQueryBuilder description = QueryBuilders.matchPhraseQuery("description", searchContent).boost(1.0F);
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(name).should(author).should(description);
        final NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();
        log.info("\n" + build.getQuery().toString());
        // TODO  与 bookRepository.searchBook(searchContent); 结果一致,只是一种自己构建的查询,一种用语法直接查询.相比起来这种方式更优雅
        final Page<Book> bookPage = bookRepository.search(build);
        return PageResult.of(bookPage.getTotalElements(), bookPage.getContent());
    }

    @Override
    public Book selectByBookNo(String bookNo) {
        return this.bookRepository.findById(bookNo).orElse(null);
    }

    @Override
    public List<Book> findByRecommendOrderByLastModifiedTime(Byte recommend) {
        return bookRepository.findByRecommendOrderByLastModifiedTime(recommend);
    }


}