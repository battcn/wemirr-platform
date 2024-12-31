package com.wemirr.platform.bpm.service;

import java.util.function.Supplier;

/**
 * Camunda流程 身份信息维护业务接口
 *
 * @author Levin
 */
public interface ProcessIdentityService {


    <T> T execute(Supplier<T> supplier);

}
