package com.battcn.management.consumer.webmagic.pageprocessor;

import com.alibaba.fastjson.JSON;
import com.battcn.framework.webmagic.utils.BrowserAgentUtil;
import com.battcn.system.pojo.po.ProxyPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.ProxyUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author Levin
 * @since 2018/3/13 0013
 */
@Slf4j
public class ProxyProcessor implements PageProcessor {

    private static final Site DEFAULT_SITE = Site.me()
            .setTimeOut(6000).setRetryTimes(3)
            .setSleepTime(1000).setCharset("UTF-8")
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
        String proxyLink = page.getUrl().get();
        List<ProxyPool> proxies = Lists.newArrayList();
        if (proxyLink.contains("crossincode")) {
            // Crossin 编程实验室 代理IP
            proxies = resolveCrossionProxy(page);
        } else if (proxyLink.contains("xicidaili")) {
            // 大象代理
            proxies = resolveXicidailiProxy(page);
            page.addTargetRequests(TARGET_REQUESTS);
        } else if (proxyLink.contains("ip181")) {
            // ip181 代理
            proxies = resolveIp181Proxy(page);
        }
        System.out.println(JSON.toJSONString(proxies));

    }

    private static List<ProxyPool> resolveIp181Proxy(Page page) {
        List<Selectable> trs = page.getHtml().xpath("//table[@class='table table-hover panel-default panel ctable']/tbody/tr").nodes();
        if (CollectionUtils.isEmpty(trs)) {
            return null;
        }
        return trs.stream().skip(1).map(node -> {
            final String host = node.xpath("//td[1]/text()").get();
            final int port = Integer.parseInt(node.xpath("//td[2]/text()").get());
            final String anonymity = node.xpath("//td[3]/text()").get();
            final String type = node.xpath("//td[4]/text()").get();
            final String location = node.xpath("//td[5]/text()").get();
            final String validateTime = node.xpath("//td[6]/text()").get();
            final boolean locked = ProxyUtils.validateProxy(new Proxy(host, port));
            return new ProxyPool(null, host, port, anonymity, type, location, validateTime, locked);
        }).collect(toList());
    }

    private static List<ProxyPool> resolveXicidailiProxy(Page page) {
        List<Selectable> trs = page.getHtml().xpath("//table[@id='ip_list']/tbody/tr").nodes();
        if (CollectionUtils.isEmpty(trs)) {
            return null;
        }
        return trs.stream().skip(1).map(node -> {
            final String type = node.xpath("//td[6]/text()").get();
            if (StringUtils.equalsIgnoreCase(HTTPS, type)) {
                final String host = node.xpath("//td[2]/text()").get();
                final int port = Integer.parseInt(node.xpath("//td[3]/text()").get());
                final String location = node.xpath("//td[4]/a/text()").get();
                final String anonymity = node.xpath("//td[5]/text()").get();
                final String validateTime = StringUtils.join("20", node.xpath("//td[10]/text()").get());
                final boolean locked = ProxyUtils.validateProxy(new Proxy(host, port));
                return new ProxyPool(null, host, port, anonymity, type, location, validateTime, locked);
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private static List<ProxyPool> resolveCrossionProxy(Page page) {
        List<Selectable> trs = page.getHtml().xpath("//table[@class='table table-bordered proxy-index-table']/tbody/tr").nodes();
        if (CollectionUtils.isEmpty(trs)) {
            return null;
        }
        return trs.stream().skip(1).map(node -> {
            final String host = node.xpath("//td[1]/text()").get();
            final int port = Integer.parseInt(node.xpath("//td[2]/text()").get());
            final String anonymity = node.xpath("//td[3]/text()").get();
            final String type = node.xpath("//td[4]/text()").get();
            final String location = node.xpath("//td[5]/text()").get();
            final String validateTime = node.xpath("//td[6]/text()").get();
            final boolean locked = ProxyUtils.validateProxy(new Proxy(host, port));
            return new ProxyPool(null, host, port, anonymity, type, location, validateTime, locked);
        }).collect(toList());
    }

    @Override
    public Site getSite() {
        return DEFAULT_SITE;
    }

    public static void main(String[] args) {
        //Spider.create(new ProxyProcessor()).addUrl("http://lab.crossincode.com/proxy/", "http://www.xicidaili.com/nn", "http://www.ip181.com/").thread(10).run();
        Spider.create(new ProxyProcessor()).addUrl("http://www.ip181.com/").thread(5).run();
    }
}