package com.battcn.system.provider.service;

import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;
import com.battcn.system.facade.ProxyPoolService;
import com.battcn.system.pojo.po.ProxyPool;

/**
 * @author Levin
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class ProxyPoolServiceImpl extends BaseServiceImpl<ProxyPool> implements ProxyPoolService {

}
