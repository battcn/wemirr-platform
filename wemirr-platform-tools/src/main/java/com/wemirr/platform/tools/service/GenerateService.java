package com.wemirr.platform.tools.service;


import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.platform.tools.domain.entity.GenerateRequest;

/**
 * @author Levin
 */
public interface GenerateService extends SuperService<GenerateRequest> {
    String generate(GenerateRequest request);
}
