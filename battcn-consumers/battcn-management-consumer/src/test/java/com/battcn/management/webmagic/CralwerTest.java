package com.battcn.management.webmagic;

import com.battcn.framework.webmagic.downloader.CrawlerDownloader;
import com.battcn.framework.webmagic.downloader.CrowProxyProvider;
import com.battcn.management.webmagic.entity.ProxyIp;
import com.battcn.management.webmagic.mapper.ProxyIpMapper;
import com.battcn.management.webmagic.pageprocessor.MovieProcessor;
import com.battcn.management.webmagic.pageprocessor.ProxyPoolProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.proxy.Proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yanglei
 *         <p>
 *         2018年3月10日
 */
public class CralwerTest {

    @Autowired
    ProxyIpMapper proxyIpMapper;

    /**
     * @param args
     */
    public static void main(String[] args) {
        CralwerTest cralwerTest = new CralwerTest();
        cralwerTest.proxyExample();
        //爬代理ip网站获取代理ip
        Spider.create(new ProxyPoolProcessor()).addUrl("http://www.xicidaili.com/nn")
                // .addUrl("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html")
                .addPipeline(new ConsolePipeline()).thread(4).run();
    }

    /**
     * 代理ip使用样例
     */
    public void proxyExample() {
        int threadNum = 5;
        List<ProxyIp> proxyList = proxyIpMapper.findAllProxies();
        List<Proxy> proxies = new ArrayList<>(proxyList.size());
        for (ProxyIp proxyIp : proxyList) {
            proxies.add(new Proxy(proxyIp.getIp(), proxyIp.getPort()));
        }

        threadNum = proxies.size();
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);

        CrawlerDownloader httpClientDownloader = new CrawlerDownloader();
        // 设置动态转发代理，使用定制的ProxyProvider
        httpClientDownloader.setProxyProvider(new CrowProxyProvider(proxies));

        Spider.create(new MovieProcessor())
                .setDownloader(new CrawlerDownloader()).addUrl("http://www.80s.tw/")
                .addPipeline(new ConsolePipeline()).thread(executorService, threadNum).run();
        System.out.println("----------------------getMovies----------------------------end");
    }
}
