package com.wemirr.framework.database.mybatis.auth;

import com.wemirr.framework.commons.entity.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Levin
 * @since 2020/2/1
 * 数据权限查询参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataScope {

    /**
     * 限制范围为个人时的字段名称
     */
    @Builder.Default
    private String selfScopeName = Entity.CREATE_USER_COLUMN;
    /**
     * 当前用户ID
     */
    private Long userId;

    /**
     * 具体的数据范围
     */
    private List<Long> orgIds;
    /**
     * 限制范围的字段名称 （除个人外）
     */
    @Builder.Default
    private String scopeName = "org_id";
    /**
     * 具体的数据范围
     */
    @Builder.Default
    private DataScopeType scopeType = DataScopeType.SELF;

}
