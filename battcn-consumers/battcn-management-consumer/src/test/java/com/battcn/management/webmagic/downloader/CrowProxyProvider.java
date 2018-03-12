package com.battcn.management.webmagic.downloader;

import com.battcn.management.webmagic.utils.IPCheckUtil;
import com.google.common.collect.ImmutableList;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * edit by yanglei on 17/12/25.
 * 自定义的ProxyProvider
 * 添加了代理有效性校验
 */
public class CrowProxyProvider implements ProxyProvider {

    private final List<Proxy> proxies;
    private final AtomicInteger pointer;

    /**
     * @return the proxies
     */
    public List<Proxy> getProxies() {
        return proxies;
    }

    public CrowProxyProvider(List<Proxy> proxies) {
        this(proxies, new AtomicInteger(-1));
    }

    private CrowProxyProvider(List<Proxy> proxies, AtomicInteger pointer) {
        List<Proxy> ProxyList = new ArrayList<>();
        for (Proxy proxy : proxies) {
            if (IPCheckUtil.checkValidIP(proxy.getHost(), proxy.getPort())) {
                ProxyList.add(proxy);
            }
        }
        this.proxies = ProxyList;
        this.pointer = pointer;
    }

    public static CrowProxyProvider from(Proxy... proxies) {
        ImmutableList immutableList = ImmutableList.of(proxies);
        return new CrowProxyProvider(immutableList);
    }

    public void returnProxy(Proxy proxy, Page page, Task task) {


    }

    public Proxy getProxy(Task task) {
        return this.proxies.get(this.incrForLoop());
    }

    private int incrForLoop() {
        int p = this.pointer.incrementAndGet();
        int size = this.proxies.size();
        if (p < size) {
            return p;
        } else {
            while (!this.pointer.compareAndSet(p, p % size)) {
                p = this.pointer.get();
            }
            return p % size;
        }
    }
}
