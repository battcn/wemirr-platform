package com.wemirr.framework.db.mybatisplus.ext;


import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * @author battcn
 */
public interface SuperService<T> extends IService<T> {

    /**
     * 默认一次处理 500 条,有特殊需要自己for处理去
     * mysql rewriteBatchedStatements=true 可以优化批量SQL，然后使用 IService.saveBatch
     * oracle 默认最大 1000
     * pgsql 按照内容大小,基本上别超出1000条数据
     *
     * @param list list
     * @return 成功与否
     */
    boolean insertBatch(List<T> list);

    /**
     * 默认一次处理 500 条,有特殊需要自己for处理去
     * mysql rewriteBatchedStatements=true 可以优化批量SQL，然后使用 IService.saveBatch
     * oracle 默认最大 1000
     * pgsql 按照内容大小,基本上别超出1000条数据
     *
     * @param list list
     * @return 成功与否
     */
    boolean updateBatch(List<T> list);

}