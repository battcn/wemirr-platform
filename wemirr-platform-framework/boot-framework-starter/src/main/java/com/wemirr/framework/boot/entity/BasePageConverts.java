package com.wemirr.framework.boot.entity;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.entity.BaseConverts;

/**
 * 对象转换接口
 *
 * @param <S> 源对象
 * @param <T> 目标对象
 * @author Levin
 * @version 1.0.0
 * @since 2019-03-19
 */
public interface BasePageConverts<S, T> extends BaseConverts<S, T> {


    /**
     * 将PO分页对象转换成VO分页对象
     *
     * @param source 原始数据
     * @return 转换后的数据
     */
    default IPage<T> convertPage(IPage<S> source) {
        if (source == null) {
            return new Page<>();
        }
        return source.convert(this::convert);
    }


}
