package com.wemirr.platform.suite.online.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.suite.online.domain.entity.OnlineFormData;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDataSaveReq;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignerPageReq;
import com.wemirr.platform.suite.online.repository.OnlineFormDataMapper;
import com.wemirr.platform.suite.online.service.OnlineFormDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OnlineFormDataServiceImpl extends SuperServiceImpl<OnlineFormDataMapper, OnlineFormData> implements OnlineFormDataService {

    @Override
    public IPage<JSONObject> pageList(OnlineFormDesignerPageReq req) {
        return this.baseMapper.pageList(req.buildPage(), req).convert(x -> new JSONObject() {{
            put("id", x.getId());
            put("definitionKey", x.getDefinitionKey());
            put("tenantId", x.getTenantId());
            put("createdName", x.getCreatedName());
            put("createdTime", x.getCreatedTime());
            putAll(JSONObject.parse(x.getFormData()));
        }});
    }

    @Override
    public void created(OnlineFormDataSaveReq req) {
        var bean = BeanUtilPlus.toBean(req, OnlineFormData.class);
        this.baseMapper.insert(bean);
    }

    @Override
    public void modify(Long id, OnlineFormDataSaveReq req) {
        var bean = BeanUtilPlus.toBean(id, req, OnlineFormData.class);
        this.baseMapper.updateById(bean);
    }
}
