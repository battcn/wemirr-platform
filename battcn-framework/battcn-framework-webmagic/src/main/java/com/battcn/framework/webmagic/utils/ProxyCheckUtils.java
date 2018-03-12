package com.battcn.framework.webmagic.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * @author Lei.Yang
 * @since 2018/01/05
 */
public class ProxyCheckUtils {

    private static final String REQUEST_METHOD = "GET";
    private static final int SUCCESS = 200;
    private static final Logger logger = LoggerFactory.getLogger(ProxyCheckUtils.class);

    /**
     * 校验代理IP的有效性，校验地址为：http://www.ip138.com
     *
     * @param ip   代理IP地址
     * @param port 代理IP端口
     * @return 此代理IP是否有效
     */
    public static boolean validIpAddress(String ip, Integer port) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://www.ip138.com");
            //代理服务器
            InetSocketAddress proxyAddress = new InetSocketAddress(ip, port);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddress);
            connection = (HttpURLConnection) url.openConnection(proxy);
            //代理ip速度要快一点  不然会造成网络堵塞   2秒差不多
            connection.setReadTimeout(2000);
            connection.setConnectTimeout(2000);
            connection.setRequestMethod(REQUEST_METHOD);
            if (connection.getResponseCode() == SUCCESS) {
                connection.disconnect();
                logger.info("IP 有效 {} - {}", ip, port);
                return true;
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return false;
    }
}
