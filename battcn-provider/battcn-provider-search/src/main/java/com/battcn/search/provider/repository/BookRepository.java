package com.battcn.search.provider.repository;

import com.battcn.search.pojo.po.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {


}