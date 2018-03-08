package com.battcn.framework.mybatis.service.impl;

import com.battcn.framework.mybatis.mapper.BaseMapper;
import com.battcn.framework.mybatis.pojo.DataGrid;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * service通用基类
 *
 * @param <T>
 * @author fly
 */
@Service
public abstract class BaseServiceImpl<T> implements com.battcn.framework.mybatis.service.BaseService<T> {

    @Autowired
    private BaseMapper<T> mapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(T entity) {
        return mapper.insertSelective(entity) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertSelective(T entity) {
        return mapper.insertSelective(entity) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteById(Object key) {
        return mapper.deleteByPrimaryKey(key) > 0;
    }

    @Override
    public T selectById(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(T entity) {
        return mapper.updateByPrimaryKey(entity) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSelectiveById(T entity) {
        return mapper.updateByPrimaryKeySelective(entity) > 0;
    }

    @Override
    public List<T> listAll() {
        return this.mapper.selectAll();
    }

    @Override
    public List<T> select(T record) {
        return this.mapper.select(record);
    }

    @Override
    public PageInfo<T> listForDataGrid(DataGrid grid) {
        return this.listForDataGrid(grid, null);
    }

    @Override
    public PageInfo<T> listForDataGrid(DataGrid grid, T entity) {
        PageHelper.startPage(grid.getPageNum(), grid.getPageSize()).setOrderBy(grid.getSort() + " " + grid.getOrder());
        return new PageInfo<T>(mapper.select(entity));
    }
}
