package com.wemirr.platform.tools.service;


import com.wemirr.framework.db.mybatis.SuperService;
import com.wemirr.platform.tools.domain.entity.GenerateEntity;
import com.wemirr.platform.tools.domain.resp.GenerateTableResp;

import java.util.List;

/**
 * @author Levin
 */
public interface GenerateService extends SuperService<GenerateEntity> {

    /**
     * 生成
     *
     * @param request request
     * @return 生成结果
     */
    String generate(GenerateEntity request);


    List<GenerateTableResp> loadTables();
}
