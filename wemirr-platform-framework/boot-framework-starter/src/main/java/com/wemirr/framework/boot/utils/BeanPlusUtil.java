package com.wemirr.framework.boot.utils;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.boot.entity.BasePageConverts;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Bean增强类工具类
 *
 * <p>
 * 把一个拥有对属性进行set和get方法的类，我们就可以称之为JavaBean。
 * </p>
 *
 * @author Levin
 * @since 3.1.2
 */
public class BeanPlusUtil extends BeanUtil {

    /**
     * 转换 list
     *
     * @param sourceList       原始数据集
     * @param destinationClass 目标对象
     * @param <T>              原始数据对象类型
     * @param <E>              目标对象类型
     * @return 转换结果
     * @see BasePageConverts （如果有枚举类型请使用该方法 ）
     */
    public static <T, E> List<T> toBeans(Collection<E> sourceList, Class<T> destinationClass) {
        if (sourceList == null || sourceList.isEmpty() || destinationClass == null) {
            return Collections.emptyList();
        }
        return sourceList.parallelStream()
                .filter(Objects::nonNull)
                .map((source) -> toBean(source, destinationClass))
                .collect(Collectors.toList());
    }

    /**
     * 转化Page 对象
     *
     * @param page             分页对象
     * @param destinationClass 转换的类
     * @param <T>              目标对象
     * @param <E>              原始对象
     * @return 转换结果集
     * @see BasePageConverts （如果有枚举类型请使用该方法 ）
     */
    public static <T, E> IPage<T> toBeanPage(IPage<E> page, Class<T> destinationClass) {
        if (page == null || destinationClass == null) {
            return null;
        }
        IPage<T> newPage = new Page<>(page.getCurrent(), page.getSize());
        newPage.setPages(page.getPages());
        newPage.setTotal(page.getTotal());
        List<E> list = page.getRecords();
        if (CollUtil.isEmpty(list)) {
            return newPage;
        }
        List<T> destinationList = toBeans(list, destinationClass);
        newPage.setRecords(destinationList);
        return newPage;
    }

}
