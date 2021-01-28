package com.wemirr.platform.tools.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Levin
 */
@Repository
public interface DynamicReleaseMapper {

    IPage<Map<String, Object>> pageList(IPage<?> page, @Param("tableName") String tableName, @Param("map") Map<String, Object> params);

    IPage<Map<String, Object>> dynamicPageList(IPage<?> page, @Param("sql") String sql);


    @Delete("delete from ${tableName} where id = #{id}")
    void deleteById(@Param("tableName") String tableName, @Param("id") Long id);

    void insertMap(@Param("tableName") String tableName, @Param("map") Map<String, Object> map);

    void updateByMap(@Param("tableName") String tableName, @Param("id") Long id, @Param("map") Map<String, Object> map);
}
