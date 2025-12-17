package com.wemirr.framework.mongodb.audit;

import cn.hutool.extra.spring.SpringUtil;
import com.wemirr.framework.commons.security.AuthenticationContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * MongoDB 审计用户提供者
 * <p>自动填充 @CreatedBy 和 @LastModifiedBy 字段</p>
 *
 * @author Levin
 */
@Slf4j
public class MongoAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        try {
            AuthenticationContext context = SpringUtil.getBean(AuthenticationContext.class);
            Long userId = context.userId();
            return Optional.ofNullable(userId);
        } catch (Exception e) {
            log.debug("Failed to get current auditor: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
