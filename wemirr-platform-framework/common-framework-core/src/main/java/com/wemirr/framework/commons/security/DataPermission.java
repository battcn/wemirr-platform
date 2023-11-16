package com.wemirr.framework.commons.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataPermission implements java.io.Serializable {

    /**
     * 权限范围
     */
    @Builder.Default
    private DataScopeType scopeType = DataScopeType.IGNORE;

    /**
     * 多维度数据权限
     */
    @Builder.Default
    private Map<DataResourceType, List<Object>> dataPermissionMap = Map.of();

}
