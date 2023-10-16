package com.wemirr.platform.tools.service.impl;

import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDrag;
import com.wemirr.platform.tools.domain.req.DynamicReleaseDragReq;
import com.wemirr.platform.tools.repository.DynamicReleaseDragMapper;
import com.wemirr.platform.tools.service.DynamicReleaseDragService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class DynamicReleaseDragServiceImpl extends SuperServiceImpl<DynamicReleaseDragMapper, DynamicReleaseDrag> implements DynamicReleaseDragService {

    private final AuthenticationContext authenticationContext;

    @Override
    public void add(DynamicReleaseDragReq req) {
        final String model = req.getModel();
        String tableName = StringUtils.join("tenant_", authenticationContext.tenantId(), "_", model);
        baseMapper.insert(DynamicReleaseDrag.builder()
                .label(req.getLabel())
                .model(model).tableName(tableName).locked(false)
                .description(req.getDescription())
                .tenantId(authenticationContext.tenantId())
                .build());
    }

    @Override
    public void edit(Long id, DynamicReleaseDragReq req) {
        DynamicReleaseDrag drag = new DynamicReleaseDrag();
        drag.setId(id);
        drag.setDescription(req.getDescription());
        drag.setLocked(req.getLocked());
        drag.setModel(req.getModel());
        drag.setLabel(req.getLabel());
        this.baseMapper.updateById(drag);
    }


}
