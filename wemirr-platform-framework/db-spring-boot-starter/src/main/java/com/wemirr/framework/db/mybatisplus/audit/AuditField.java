package com.wemirr.framework.db.mybatisplus.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditField {

    /**
     * 字段名
     */
    private String field;

    /**
     * 注释
     */
    private String label;
    /**
     * 修改前数据
     */
    private Object source;

    /**
     * 修改后数据
     */
    private Object target;

    /**
     * 格式化内容
     */
    private String format;

    public String getFormat() {
        return String.format("字段 [%s] 从 %s 修改至 %s ", label, source, target);
    }
}
