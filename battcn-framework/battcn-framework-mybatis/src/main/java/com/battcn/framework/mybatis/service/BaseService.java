package com.battcn.framework.mybatis.service;

import com.battcn.framework.mybatis.pojo.DataGrid;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @param <T> 泛型
 * @author Levin
 *         公共的业务方法
 */
public interface BaseService<T> {

    /**
     * 新增一个实体，方法的实现需保证：当返回true时，实体entity的id属性已被赋值。
     *
     * @param entity 实体对象
     * @return 操作结果
     */
    boolean insert(T entity);

    /**
     * Null 字段使用数据库默认值
     *
     * @param entity 实体对象
     * @return 操作结果
     */
    boolean insertSelective(T entity);

    /**
     * 根据主键删除一个实体
     *
     * @param key 主键
     * @return 操作结果
     */
    boolean deleteById(Object key);

    /**
     * 根据主键字段进行查询
     *
     * @param key 主键
     * @return 操作结果
     */
    T selectById(Object key);

    /**
     * 根据主键更新实体全部字段(TODO 公共属性部分不必赋值，入库拦截前会自动将修改人 修改时间加上)
     *
     * @param entity 实体对象
     * @return 操作结果
     * @author fly
     */
    boolean updateById(T entity);

    /**
     * 根据主键更新不为NUll的值
     *
     * @param entity 实体对象
     * @return 操作结果
     * @author fly
     */
    boolean updateSelectiveById(T entity);

    /**
     * 查询所有
     *
     * @return 操作结果
     */
    List<T> listAll();

    /**
     * 用实体作为条件对象进行查询
     *
     * @param record 实体对象
     * @return 查询结果
     */
    List<T> select(T record);

    /**
     * 分页信息查询列表
     *
     * @param grid 分页对象
     * @return 查询结果集合
     */
    PageInfo<T> listForDataGrid(DataGrid grid);

    /**
     * 根据分页信息及实体查询所有
     *
     * @param grid   分页对象
     * @param entity 查询对象
     * @return 查询结果集合
     */
    PageInfo<T> listForDataGrid(DataGrid grid, T entity);
}
