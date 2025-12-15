package com.wemirr.framework.db.mybatisplus.audit;

import cn.hutool.core.util.StrUtil;

/**
 * 审计字段变更记录
 * <p>
 * 记录单个字段的变更信息，包含字段名、显示名、变更前后的值
 *
 * @author Levin
 */
public record AuditField(
        String field,
        String label,
        Object source,
        Object target,
        String format
) {
    /**
     * 获取格式化的变更描述
     *
     * @return 变更描述文本
     */
    public String formattedDescription() {
        if (StrUtil.isNotBlank(format)) {
            return format;
        }
        return "字段 [%s] 从 %s 修改至 %s".formatted(label, source, target);
    }
}
