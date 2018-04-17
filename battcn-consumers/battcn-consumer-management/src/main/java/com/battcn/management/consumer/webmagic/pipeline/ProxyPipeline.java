package com.battcn.management.consumer.webmagic.pipeline;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.system.facade.ProxyPoolService;
import com.battcn.system.pojo.po.ProxyPool;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Levin
 * @since 2018/3/12 0012
 */
@Component
public class ProxyPipeline implements Pipeline {

    private static final String PROXIES = "proxies";

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "${dubbo.registry.address}")
    private ProxyPoolService proxyPoolService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (resultItems == null) {
            return;
        }
        final List<ProxyPool> proxies = resultItems.get(PROXIES);
        if (CollectionUtils.isEmpty(proxies)) {
            return;
        }
        // 过滤掉无效代理
        final List<ProxyPool> proxyPools = proxies.stream()
                .filter(ProxyPool::getLocked)
                .filter(proxy -> {
            ProxyPool record = new ProxyPool();
            record.setHost(proxy.getHost());
            record.setPort(proxy.getPort());
            final List<ProxyPool> select = proxyPoolService.select(record);
            // 表示 数据库还没这个IP
            return CollectionUtils.isEmpty(select);
        }).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(proxyPools)) {
            return;
        }
        proxyPoolService.batchInsertProxies(proxyPools);
    }

}
