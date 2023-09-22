package com.wemirr.platform.tools.service;


import com.wemirr.framework.db.mybatisplus.ext.SuperService;
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

    /**
     * 显示所有表
     *
     * @return 表名
     */
    List<GenerateTableResp> loadTables();
}
