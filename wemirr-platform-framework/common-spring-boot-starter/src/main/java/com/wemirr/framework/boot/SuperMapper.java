package com.wemirr.framework.boot;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 基于MP的 BaseMapper 新增了2个方法： insertBatchSomeColumn、updateAllById
 *
 * @param <T> 实体
 * @author Levin
 * @since 2020-04-02
 */
@Repository
public interface SuperMapper<T> extends BaseMapper<T> {


}
