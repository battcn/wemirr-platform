package com.wemirr.platform.tools.service;


import com.wemirr.framework.db.mybatis.SuperService;
import com.wemirr.platform.tools.domain.entity.GenerateRequest;

/**
 * @author Levin
 */
public interface GenerateService extends SuperService<GenerateRequest> {

    /**
     * 生成
     *
     * @param request request
     * @return 生成结果
     */
    String generate(GenerateRequest request);
}
