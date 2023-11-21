package com.wemirr.platform.flex.configuration;

import com.mybatisflex.core.audit.AuditManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author Levin
 */
@Slf4j
@Configuration
public class MyBatisFlexConfiguration {

    public MyBatisFlexConfiguration() {
        // 开启审计功能
        AuditManager.setAuditEnable(true);
        // 设置 SQL 审计收集器
        AuditManager.setMessageCollector(auditMessage -> log.info("{},{},{} ms", auditMessage.toString(), auditMessage.getFullSql(), auditMessage.getElapsedTime()));
    }

}
