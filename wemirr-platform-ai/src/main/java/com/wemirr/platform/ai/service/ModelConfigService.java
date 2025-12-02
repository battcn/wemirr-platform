package com.wemirr.platform.ai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.ai.domain.dto.rep.ModelConfigDetailRep;
import com.wemirr.platform.ai.domain.dto.rep.ModelConfigPageRep;
import com.wemirr.platform.ai.domain.dto.req.ModelConfigPageReq;
import com.wemirr.platform.ai.domain.dto.req.ModelConfigSaveReq;
import com.wemirr.platform.ai.domain.entity.ModelConfig;

/**
 * @author xJh
 * @since 2025/10/11
 **/
public interface ModelConfigService extends SuperService<ModelConfig> {

    IPage<ModelConfigPageRep> pageList(ModelConfigPageReq req);

    ModelConfigDetailRep detail(Long id);

    void create(ModelConfigSaveReq req);

    void modify(Long id, ModelConfigSaveReq req);

    void remove(Long id);
}
