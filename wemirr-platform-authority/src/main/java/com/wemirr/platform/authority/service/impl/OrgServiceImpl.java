package com.wemirr.platform.authority.service.impl;

import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.authority.domain.baseinfo.entity.Org;
import com.wemirr.platform.authority.repository.baseinfo.OrgMapper;
import com.wemirr.platform.authority.service.OrgService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 业务实现类
 * 组织
 * </p>
 *
 * @author Levin
 * @since 2019-07-22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrgServiceImpl extends SuperServiceImpl<OrgMapper, Org> implements OrgService {

    private final AuthenticationContext authenticationContext;

    @Override
    public void remove(Long id) {
        final Long count = this.baseMapper.selectCount(Org::getParentId, id);
        if (count != null && count > 0) {
            throw CheckedException.badRequest("当前组织下还存在子节点,请先移除子节点");
        }
        this.baseMapper.deleteById(id);
    }

    @Override
    public void addOrg(@NotNull Org org) {
        if (org == null) {
            throw CheckedException.notFound("信息不能为空");
        }
        org.setTenantId(authenticationContext.tenantId());
        this.baseMapper.insert(org);
    }
}
