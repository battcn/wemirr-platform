package com.battcn.framework.mybatis.mapper;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 封装了通用Mybatis的操作,针对Mysql的BaseMapper
 *
 * @author Levin
 * @version 1.0.0
 * @since 2018-01-20
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, ConditionMapper<T> {
}
