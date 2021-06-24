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

    /**
     * 分页查询
     *
     * @param page      分页对象
     * @param tableName 表名
     * @param params    查询参数
     * @return 查询结果
     */
    IPage<Map<String, Object>> pageList(IPage<?> page, @Param("tableName") String tableName, @Param("map") Map<String, Object> params);

    /**
     * 动态分页列表
     *
     * @param page page
     * @param sql  sql
     * @return 查询结果
     */
    IPage<Map<String, Object>> dynamicPageList(IPage<?> page, @Param("sql") String sql);


    /**
     * 根据ID和表名删除
     *
     * @param tableName 表名
     * @param id        id
     */
    @Delete("delete from ${tableName} where id = #{id}")
    void deleteById(@Param("tableName") String tableName, @Param("id") Long id);

    /**
     * 插入 map 数据
     *
     * @param tableName 表名
     * @param map       map数据
     */
    void insertMap(@Param("tableName") String tableName, @Param("map") Map<String, Object> map);

    /**
     * 动态修改
     *
     * @param tableName 表名
     * @param id        id
     * @param map       map数据
     */
    void updateByMap(@Param("tableName") String tableName, @Param("id") Long id, @Param("map") Map<String, Object> map);
}
