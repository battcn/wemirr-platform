package com.wemirr.framework.job;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SwaggerProperties
 *
 * @author Levin
 */
@Data
@ConfigurationProperties(prefix = "extend.xxl.job")
public class XxlJobProperties {

    /**
     * 调度中心部署跟地址 [选填]：如调度中心集群部署存在多个地址则用逗号分隔。执行器将会使用该地址进行"执行器心跳注册"和"任务结果回调"；为空则关闭自动注册；
     */
    private String adminAddresses;
    /**
     * ### 执行器通讯TOKEN [选填]：非空时启用；
     */
    private String accessToken;
    private Executor executor;


    @Data
    public static class Executor {
        /**
         * ### 执行器AppName [选填]：执行器心跳注册分组依据；为空则关闭自动注册
         */
        private String appName;
        /**
         * ### 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯实用；地址信息用于 "执行器注册" 和 "调度中心请求并触发任务"；
         */
        private String address;
        /**
         * ### 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯实用；地址信息用于 "执行器注册" 和 "调度中心请求并触发任务"；
         * 针对多网卡、容器内部署等情况，可借助 "spring-cloud-commons" 提供的 "InetUtils" 组件灵活定制注册IP；
         * *      1、引入依赖：
         * *          <dependency>
         * *             <groupId>org.springframework.cloud</groupId>
         * *             <artifactId>spring-cloud-commons</artifactId>
         * *             <version>${version}</version>
         * *         </dependency>
         * *
         * *      2、配置文件，或者容器启动变量
         * *          spring.cloud.inetutils.preferred-networks: 'xxx.xxx.xxx.'
         * *
         * *      3、获取IP
         * *          String ip_ = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
         */
        private String ip;
        /**
         * ### 执行器端口号 [选填]：小于等于0则自动获取；默认端口为9999，单机部署多个执行器时，注意要配置不同执行器端口；
         */
        private int port = 9999;
        /**
         * ### 执行器运行日志文件存储磁盘路径 [选填] ：需要对该路径拥有读写权限；为空则使用默认路径；
         */
        private String logPath;
        /**
         * ### 执行器日志文件保存天数 [选填] ： 过期日志自动清理, 限制值大于等于3时生效; 否则, 如-1, 关闭自动清理功能；
         */
        private int logRetentionDays;

    }


}
