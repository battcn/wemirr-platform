package com.battcn.management.webmagic.downloader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.battcn.management.webmagic.utils.IPCheckUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;

/**
 * Created by CrowHawk on 17/10/16.
 */

/**edit by yanglei on 17/12/25.
 * 自定义的ProxyProvider
 * 添加了代理有效性校验
 */
public class CrowProxyProvider implements ProxyProvider{
	
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
        int size = proxies.size();
        List<Proxy> ProxyList=new ArrayList<>();
        for(int i = 0; i < size; ++i) {
            Proxy proxy = proxies.get(i);
            if(IPCheckUtil.checkValidIP(proxy.getHost(), proxy.getPort())) {
            	ProxyList.add(proxy);
            }
        }
        this.proxies = ProxyList;
        this.pointer = pointer;
    }

    public static CrowProxyProvider from(Proxy... proxies) {
        ArrayList proxiesTemp = new ArrayList(proxies.length);
        proxiesTemp.trimToSize();
        return new CrowProxyProvider(Collections.unmodifiableList(proxiesTemp));
    }

    public void returnProxy(Proxy proxy, Page page, Task task) {
    	 /*if(this.proxies.size()<=2){
			synchronized (this) {
				List<ProxyIp> proxyList = proxyIpMapper.findAllProxies();
				proxies.addAll(transition(proxyList));
				HashSet h = new HashSet(proxies);
				proxies.clear();
				proxies.addAll(h);
				System.out.println("-----------------------" + JSON.toJSONString(proxies));
			}
         }*/
    	 
    }
    
    
 /*   public List<Proxy> transition(List<ProxyIp> proxyList){
    	 List<Proxy> proxies = new ArrayList<>(proxyList.size());
    	 for(int i = 0; i < proxyList.size(); ++i) {
    		 
             if(IPCheckUtil.checkValidIP(proxy.getIp(), proxy.getPort())) {
            	 proxies.add(new Proxy(proxy.getIp(), proxy.getPort()));
             }
         }
    	 return proxies;
    	
    }*/
    public Proxy getProxy(Task task) {
        return (Proxy)this.proxies.get(this.incrForLoop());
    }

    private int incrForLoop() {
        int p = this.pointer.incrementAndGet();
        int size = this.proxies.size();
        if(p < size) {
            return p;
        } else {
            while(!this.pointer.compareAndSet(p, p % size)) {
                p = this.pointer.get();
            }

            return p % size;
        }
    }
}
