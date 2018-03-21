package com.battcn.management.consumer.task;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.framework.webmagic.downloader.CrawlerDownloader;
import com.battcn.management.consumer.webmagic.pageprocessor.ProxyProcessor;
import com.battcn.management.consumer.webmagic.pipeline.ProxyPipeline;
import com.battcn.system.facade.ProxyPoolService;
import com.battcn.system.pojo.po.ProxyPool;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.utils.ProxyUtils;

import java.util.List;
import java.util.concurrent.Executors;


/**
 * 动态获取代理
 *
 * @author Levin
 * @since 2018/3/17 0017
 */
@Component
public class ProxyPoolTask {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private ProxyPoolService proxyPoolService;
    private final ProxyPipeline proxyPipeline;

    @Autowired
    public ProxyPoolTask(ProxyPipeline proxyPipeline) {
        this.proxyPipeline = proxyPipeline;
    }

    /**
     * 每隔20分钟检查一次代理池
     */
    @Scheduled(cron = "0 0/20 * * * ?")
    public void updateProxies() {
        final List<ProxyPool> pools = this.proxyPoolService.listAll();
        if (CollectionUtils.isEmpty(pools)) {
            return;
        }
        pools.forEach(proxy -> {
            final boolean locked = ProxyUtils.validateProxy(new Proxy(proxy.getHost(), proxy.getPort()));
            // 代理失效
            if (!locked) {
                proxyPoolService.deleteById(proxy.getId());
            }
        });
    }

    /**
     * 每隔10分钟抓取一次代理
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void proxies() {
        Executors.newSingleThreadExecutor().execute(() -> Spider.create(new ProxyProcessor()).setDownloader(new CrawlerDownloader()).addUrl("https://proxy.coderbusy.com/classical/anonymous-type/highanonymous.aspx", "http://lab.crossincode.com/proxy/", "http://www.xicidaili.com/nn", "http://www.ip181.com/").addPipeline(proxyPipeline).thread(10).run());
    }


}
