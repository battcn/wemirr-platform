package com.battcn.system.provider.mapper;

import com.battcn.book.pojo.po.BookChapter;
import com.battcn.framework.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Mapper
@Repository
public interface BookChapterMapper extends BaseMapper<BookChapter> {


    /**
     * 根据书籍编号查询章节信息
     *
     * @param bookNo 书籍编号
     * @return 章节信息
     */
    @Select("SELECT id,title,content,source,target FROM t_book_chapter WHERE book_no = #{bookNo}")
    List<BookChapter> selectByBookNo(@Param("bookNo") String bookNo);


}