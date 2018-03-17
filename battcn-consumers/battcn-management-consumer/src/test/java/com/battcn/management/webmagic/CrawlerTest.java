package com.battcn.management.webmagic;

import com.battcn.framework.webmagic.downloader.CrawlerDownloader;
import com.battcn.framework.webmagic.downloader.CrowProxyProvider;
import com.battcn.management.webmagic.pageprocessor.MovieProcessor;
import com.battcn.system.facade.ProxyPoolService;
import com.battcn.system.pojo.po.ProxyPool;
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
public class CrawlerTest {

    @Autowired
    ProxyPoolService proxyPoolService;

    /**
     * @param args
     */
    /*public static void main(String[] args) {
        CrawlerTest cralwerTest = new CrawlerTest();
        cralwerTest.proxyExample();
        //爬代理ip网站获取代理ip
        Spider.create(new ProxyProcessor()).addUrl("http://www.xicidaili.com/nn").addPipeline(new ConsolePipeline()).thread(4).run();
    }*/

    /**
     * 代理ip使用样例
     */
    private void proxyExample() {
        int threadNum = 5;
        List<ProxyPool> proxyList = proxyPoolService.listAll();
        List<Proxy> proxies = new ArrayList<>(proxyList.size());
        for (ProxyPool proxyPool : proxyList) {
            proxies.add(new Proxy(proxyPool.getHost(), proxyPool.getPort()));
        }

        threadNum = proxies.size();
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);

        CrawlerDownloader httpClientDownloader = new CrawlerDownloader();
        // 设置动态转发代理，使用定制的ProxyProvider
        httpClientDownloader.setProxyProvider(new CrowProxyProvider(proxies));

        Spider.create(new MovieProcessor())
                .setDownloader(httpClientDownloader).addUrl("http://www.80s.tw/")
                .addPipeline(new ConsolePipeline()).thread(executorService, threadNum).run();
        System.out.println("----------------------getMovies----------------------------end");


    }
}
