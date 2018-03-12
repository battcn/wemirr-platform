package com.battcn.management.webmagic.utils;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * Created by CrowHawk on 17/10/16.
 * edit by yanglei on 18/01/05
 */
public class IPCheckUtil {

    /**
     * 校验代理IP的有效性，测试地址为：http://www.ip138.com
     *
     * @param ip   代理IP地址
     * @param port 代理IP端口
     * @return 此代理IP是否有效
     */
    public static boolean checkValidIP(String ip, Integer port) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL("http://www.ip138.com");
            //代理服务器
            InetSocketAddress proxyAddress = new InetSocketAddress(ip, port);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddress);
            connection = (HttpURLConnection) url.openConnection(proxy);
            //代理ip速度要快一点  不然会造成网络堵塞   2秒差不多
            connection.setReadTimeout(2000);
            connection.setConnectTimeout(2000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200) {
                connection.disconnect();
                System.out.println("IPCheckUtil   有效ip" + ip + "  " + port);
                return true;
            }

        } catch (Exception e) {
            if (connection != null) {
                connection.disconnect();
            }
            return false;
        }
        return false;
    }
}
