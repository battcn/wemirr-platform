package com.battcn.system.provider.mapper;

import com.battcn.book.pojo.po.Book;
import com.battcn.book.pojo.po.BookType;
import com.battcn.framework.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Mapper
@Repository
public interface BookMapper extends BaseMapper<Book> {

    /**
     * 分组查询书籍分类,按照数量排序
     *
     * @return 结果集
     */
    @Select("SELECT type,count(1) `count` FROM t_book GROUP BY type ORDER BY `count` DESC")
    List<BookType> queryBookTypeForList();
}