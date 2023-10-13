package com.wemirr.platform.bury.service.impl;

import com.wemirr.framework.db.mybatisplus.SuperServiceImpl;
import com.wemirr.platform.bury.domain.entity.OptLog;
import com.wemirr.platform.bury.repository.OptLogMapper;
import com.wemirr.platform.bury.service.OptLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OptLogServiceImpl extends SuperServiceImpl<OptLogMapper, OptLog> implements OptLogService {


}
