package com.battcn.system.provider.mapper;

import com.battcn.book.pojo.po.BookChapter;
import com.battcn.framework.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BookChapterMapper extends BaseMapper<BookChapter> {

}