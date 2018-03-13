package com.battcn.management.consumer.webmagic.pageprocessor;

import com.battcn.framework.webmagic.utils.BrowserAgentUtil;
import com.battcn.system.pojo.po.ProxyPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.ProxyUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Lei.Yang, Levin
 * @since 2018/03/08
 */
@Slf4j
public class ProxyPoolProcessor implements PageProcessor {

    private static final Site DEFAULT_SITE = Site.me()
            .setTimeOut(6000).setRetryTimes(3)
            .setSleepTime(1000).setCharset("UTF-8")
            .addHeader("Accept-Encoding", "/")
            .setUserAgent(BrowserAgentUtil.getBrowserAgent());
    private static final String HTTPS = "HTTPS";
    private static final double SPEED = 2.0;
    private static final List<String> TARGET_REQUESTS = Lists.newArrayList("http://www.xicidaili.com/nt/1",
            "http://www.xicidaili.com/nt/2",
            "http://www.xicidaili.com/nt/3",
            "http://www.xicidaili.com/nt/4",
            "http://www.xicidaili.com/nt/5",
            "http://www.xicidaili.com/nt/6");

    @Override
    public void process(Page page) {
        List<String> ips = page.getHtml().xpath("//table[@id='ip_list']/tbody/tr").all();
        if (CollectionUtils.isEmpty(ips)) {
            return;
        }
        List<ProxyPool> result = ips.stream().skip(1).map(ip -> {
            Html html = Html.create(ip);
            ProxyPool proxyPool = new ProxyPool();
            String[] data = html.xpath("//body/text()").get().trim().split("\\s+");
            // 初步筛选 筛选https 速度小于2秒的
            String ipType = data[3];
            String ipSpeed = html.xpath("//div[@class='bar']/@title").get();
            ipSpeed = ipSpeed.substring(0, ipSpeed.indexOf('秒'));
            double speed = Double.parseDouble(ipSpeed);
            proxyPool.setHost(data[0]);
            proxyPool.setPort(Integer.valueOf(data[1]));
            System.out.println(proxyPool.getHost() + "  " + proxyPool.getPort());
            log.debug("[抓取数据] - [{}] - [{}]", proxyPool.getId(), proxyPool.getPort());
            if (ipType.equals(HTTPS) && speed <= SPEED && ProxyUtils.validateProxy(new Proxy(proxyPool.getHost(), proxyPool.getPort()))) {
                // 质量ip检验
                log.info("[有效数据] - [{}] - [{}]", proxyPool.getId(), proxyPool.getPort());
                return proxyPool;
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        page.putField("result", result);
        page.addTargetRequests(TARGET_REQUESTS);
    }

    @Override
    public Site getSite() {
        return DEFAULT_SITE;
    }
}
