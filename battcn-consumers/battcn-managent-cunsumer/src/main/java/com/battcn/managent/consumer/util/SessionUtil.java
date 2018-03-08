package com.battcn.managent.consumer.util;

import com.battcn.system.pojo.dto.ManagerDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * Session操作工具类
 *
 * @author Levin
 * @since 2018-01-10
 */
public class SessionUtil {

    /**
     * 用户信息key
     */
    public static final String USER_SESSION_KEY = "USER_SESSION_KEY";
    public static final String USER_SESSION_LOGOUT_LOGOUT_KEY = "USER_SESSION_LOGOUT_LOGOUT_KEY";


    /**
     * 从session中获取当前登录的用户信息
     *
     * @return 用户信息
     */
    public static ManagerDto getSession() {
        Session session = SecurityUtils.getSubject().getSession();
        return (ManagerDto) session.getAttribute(USER_SESSION_KEY);
    }

    public static void setSession(ManagerDto manager) {
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(USER_SESSION_KEY, manager);
    }

}
