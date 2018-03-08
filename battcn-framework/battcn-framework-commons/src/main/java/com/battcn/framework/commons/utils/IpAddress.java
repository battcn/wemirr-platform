package com.battcn.framework.commons.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;


/**
 * 获取IP地址
 *
 * @author Levin
 * @since 2018-01-10
 */
public class IpAddress {

    /**
     * 获取内网IP
     */
    public static final String INTRANET_IP = getIntranetIp();
    /**
     * 获取外网IP
     */
    public static final String INTERNET_IP = getInternetIp();

    private IpAddress() {
    }

    /**
     * 获得内网IP
     *
     * @return 内网IP
     */
    private static String getIntranetIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得外网IP
     *
     * @return 外网IP
     */
    private static String getInternetIp() {
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            Enumeration<InetAddress> address;
            while (networks.hasMoreElements()) {
                address = networks.nextElement().getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
                    if (ip != null && ip instanceof Inet4Address && ip.isSiteLocalAddress()
                            && !ip.getHostAddress().equals(INTRANET_IP)) {
                        return ip.getHostAddress();
                    }
                }
            }
            // 如果没有外网IP，就返回内网IP
            return INTRANET_IP;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
