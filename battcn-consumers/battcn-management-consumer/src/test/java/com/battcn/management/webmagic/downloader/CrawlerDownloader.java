/**
 * 
 */
package com.battcn.management.webmagic.downloader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.downloader.HttpClientGenerator;
import us.codecraft.webmagic.downloader.HttpClientRequestContext;
import us.codecraft.webmagic.downloader.HttpUriRequestConverter;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.CharsetUtils;
import us.codecraft.webmagic.utils.HttpClientUtils;

/**
 * @author yanglei 2018年1月22日
 * 
 *         自己改装的Downloader 因为自带的可能会出现readtime out soket_time out connect time
 *         out 等等待时间过长的问题 而且无法设置时间间隔
 */
@Contract(threading=ThreadingBehavior.SAFE)
public class CrawlerDownloader extends AbstractDownloader {
	//错误地址
	 private final Map<String, String> ErrorRequestUrl = new HashMap<String, String>();
	 
	//无效代理ip
	 private final Map<Proxy, Integer> ErrorProxy = new HashMap<Proxy, Integer>();
	 //无效代理ip次数统计
	 private AtomicInteger ErrorProxySum=new AtomicInteger(1);
	 
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<String, CloseableHttpClient> httpClients = new HashMap<String, CloseableHttpClient>();

    private HttpClientGenerator httpClientGenerator = new HttpClientGenerator();

    private HttpUriRequestConverter httpUriRequestConverter = new HttpUriRequestConverter();
    
    private ProxyProvider proxyProvider;

    private boolean responseHeader = true;
   

    public void setHttpUriRequestConverter(HttpUriRequestConverter httpUriRequestConverter) {
        this.httpUriRequestConverter = httpUriRequestConverter;
    }

    public void setProxyProvider(ProxyProvider proxyProvider) {
        this.proxyProvider = proxyProvider;
    }

    private CloseableHttpClient getHttpClient(Site site) {
        if (site == null) {
            return httpClientGenerator.getClient(null);
        }
        String domain = site.getDomain();
        CloseableHttpClient httpClient = httpClients.get(domain);
        if (httpClient == null) {
            synchronized (this) {
                httpClient = httpClients.get(domain);
                if (httpClient == null) {
                    httpClient = httpClientGenerator.getClient(site);
                    httpClients.put(domain, httpClient);
                }
            }
        }
        return httpClient;
    }

    @Override
    public Page download(Request request, Task task) {
        if (task == null || task.getSite() == null) {
            throw new NullPointerException("task or site can not be null");
        }
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient httpClient = getHttpClient(task.getSite());
        Proxy proxy = proxyProvider != null ? proxyProvider.getProxy(task) : null;
        //将request和site和代理ip转换可用的HttpUriRequest和HttpClientContext以供httpClient执行使用
        HttpClientRequestContext requestContext = httpUriRequestConverter.convert(request, task.getSite(), proxy);
        Page page = Page.fail();
        try {
        	Long startTime=System.currentTimeMillis();
        	
            httpResponse = httpClient.execute(requestContext.getHttpUriRequest(), requestContext.getHttpClientContext());
            logger.info("所用时间 {}", System.currentTimeMillis()-startTime);
            page = handleResponse(request, request.getCharset() != null ? request.getCharset() : task.getSite().getCharset(), httpResponse, task);
            onSuccess(request);
            logger.info("downloading page success {}", request.getUrl());
            return page;
        } catch (IOException e) {
            logger.warn("download page {} error", request.getUrl(), e);
            onError(request);
          /* //如果代理ip失效则删除该ip   代理ip少于3重新爬取代理ip
           if((e.getMessage()!=null&&(e.getMessage().contains("Connect to")||e.getMessage().contains("SSL")))||page.getStatusCode()==403){
            synchronized (task) {
            		if(((CrowProxyProvider)proxyProvider).getProxies().size()>5){
            			if(ErrorProxy.get(proxy)!=null){
            				int i=ErrorProxySum.incrementAndGet();
            				ErrorProxy.put(proxy, i);
            				if(ErrorProxy.get(proxy)>5){
	            				((CrowProxyProvider)proxyProvider).getProxies().remove(proxy);
	                    		logger.info("删除代理ip {}",proxy.getHost()+""+proxy.getPort() );
	                    		logger.info("剩余代理ip {}",((CrowProxyProvider)proxyProvider).getProxies().size() );
            				}
            			}else{
            				ErrorProxy.put(proxy, ErrorProxySum.getAndSet(1));
            			}
            			
            		}
            		
				}
            	
            }*/
            return page;
        } finally {
            if (httpResponse != null) {
                //确保连接被释放到池中
                EntityUtils.consumeQuietly(httpResponse.getEntity());
            }
            if (proxyProvider != null && proxy != null) {
                proxyProvider.returnProxy(proxy, page, task);
            }
        }
    }

    @Override
    public void setThread(int thread) {
        httpClientGenerator.setPoolSize(thread);
    }

    protected Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException {
        byte[] bytes = IOUtils.toByteArray(httpResponse.getEntity().getContent());
        String contentType = httpResponse.getEntity().getContentType() == null ? "" : httpResponse.getEntity().getContentType().getValue();
        Page page = new Page();
        page.setBytes(bytes);
        if (!request.isBinaryContent()){
            if (charset == null) {
                charset = getHtmlCharset(contentType, bytes);
            }
            page.setCharset(charset);
            page.setRawText(new String(bytes, charset));
        }
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        page.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        page.setDownloadSuccess(true);
        if (responseHeader) {
            page.setHeaders(HttpClientUtils.convertHeaders(httpResponse.getAllHeaders()));
        }
        return page;
    }

    private String getHtmlCharset(String contentType, byte[] contentBytes) throws IOException {
        String charset = CharsetUtils.detectCharset(contentType, contentBytes);
        if (charset == null) {
            charset = Charset.defaultCharset().name();
            logger.warn("Charset autodetect failed, use {} as charset. Please specify charset in Site.setCharset()", Charset.defaultCharset());
        }
        return charset;
    }
    
    protected void onSuccess(Request request) {
    	String url=request.getUrl();
    	if(ErrorRequestUrl.get(url)!=null){
    		ErrorRequestUrl.remove(url);
    	}
    }

    protected void onError(Request request) {
    	String url=request.getUrl();
    	if(ErrorRequestUrl.get(url)==null){
    		ErrorRequestUrl.put(url, url);
    	}
    	 logger.info("现在有 {} 条链接跳转失败",ErrorRequestUrl.size() );
    }

}

