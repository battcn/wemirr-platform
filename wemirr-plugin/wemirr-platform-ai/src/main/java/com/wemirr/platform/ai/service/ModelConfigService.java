package com.wemirr.platform.ai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.ai.domain.dto.rep.ModelDetailRep;
import com.wemirr.platform.ai.domain.dto.rep.ModelPageRep;
import com.wemirr.platform.ai.domain.dto.req.ModelPageReq;
import com.wemirr.platform.ai.domain.dto.req.ModelSaveReq;
import com.wemirr.platform.ai.domain.entity.ModelEntity;

/**
 * @author xJh
 * @since 2025/10/11
 **/
public interface ModelConfigService extends SuperService<ModelEntity> {

    IPage<ModelPageRep> pageList(ModelPageReq req);

    ModelDetailRep detail(Long id);

    void create(ModelSaveReq req);

    void modify(Long id, ModelSaveReq req);

    void remove(Long id);
}
