package com.battcn.system.facade;


import com.battcn.framework.mybatis.service.BaseService;
import com.battcn.system.pojo.po.ProxyPool;

import java.util.List;

/**
 * @author Levin
 */
public interface ProxyPoolService extends BaseService<ProxyPool> {

    /**
     * 批量添加
     *
     * @param proxies 代理池
     */
    void batchInsertProxies(List<ProxyPool> proxies);
}
