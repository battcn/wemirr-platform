package com.battcn.book.provider.mapper;

import com.battcn.book.pojo.po.BookLike;
import com.battcn.framework.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Mapper
@Repository
public interface BookLikeMapper extends BaseMapper<BookLike> {

}