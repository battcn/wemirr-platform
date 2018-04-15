package com.battcn.search.provider.repository;

import com.battcn.search.pojo.po.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {

    /**
     * 查询推荐数据
     *
     * @return 查询结果
     */
    List<Book> findByRecommendTrueOrderByLastModifiedTime();
}