package com.wemirr.platform.authority.domain.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionResp {

    private List<Long> resIdList;
    private List<VueRouter> buttons;


}
