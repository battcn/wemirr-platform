package com.battcn.system.provider.service;

import com.alibaba.fastjson.JSON;
import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;
import com.battcn.system.facade.ProxyPoolService;
import com.battcn.system.pojo.po.ProxyPool;
import com.battcn.system.provider.mapper.ProxyPoolMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.List;

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

    private final ProxyPoolMapper proxyPoolMapper;

    @Autowired
    public ProxyPoolServiceImpl(ProxyPoolMapper proxyPoolMapper) {
        this.proxyPoolMapper = proxyPoolMapper;
    }


    @Override
    public void batchInsertProxies(@NotNull List<ProxyPool> proxies) {
        if (proxies != null && proxies.size() > 0) {
            proxyPoolMapper.batchInsertProxies(proxies);
        }
    }

}
