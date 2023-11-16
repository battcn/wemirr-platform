package com.wemirr.framework.db.mybatisplus.datascope.handler;


import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.security.DataResourceType;
import com.wemirr.framework.commons.security.DataScopeType;
import lombok.*;

import java.util.List;

/**
 * 数据权限的规则抽象类
 *
 * @author Levin
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataPermissionRule {

    /**
     * 是否忽略数据权限拦截
     */
    private boolean ignore;


    /**
     * 规则字段
     */
    private List<Column> columns;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Column {
        /**
         * 表别名
         */
        private String alias;

        /**
         * 字段名称
         */
        @Builder.Default
        private String name = Entity.CREATE_USER_COLUMN;


        @Builder.Default
        private Class<?> javaClass = Long.class;

        /**
         * 权限资源范围(默认创建人)
         */
        @Builder.Default
        private DataResourceType resource = DataResourceType.USER;

        /**
         * 权限范围（默认跟随系统，如果指定了就跟着指定走）
         */
        private DataScopeType scopeType = DataScopeType.IGNORE;

    }


}
