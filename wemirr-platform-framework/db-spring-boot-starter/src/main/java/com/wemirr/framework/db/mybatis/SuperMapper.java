package com.wemirr.framework.db.mybatis;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * 基于MP的 BaseMapper 新增了2个方法： insertBatchSomeColumn、updateAllById
 *
 * @param <T> 实体
 * @author Levin
 * @since 2020-04-02
 */
@Repository
public interface SuperMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入 仅适用于 最好是 mysql 其他数据库有可能有问题
     *
     * @param entityList 实体列表
     * @return 影响行数
     */
    int insertBatchSomeColumn(Collection<?> entityList);

    /**
     * 批量修改 仅适用于 最好是 mysql 其他数据库有可能有问题
     *
     * @param entityList 实体列表
     * @return 影响行数
     */
    int updateBatchSomeColumnById(Collection<?> entityList);

}
