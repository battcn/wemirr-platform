package com.wemirr.platform.bpm.service.impl;

import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.bpm.domain.entity.ProcessInstanceExt;
import com.wemirr.platform.bpm.repository.ProcessInstanceExtMapper;
import com.wemirr.platform.bpm.service.ProcessInstanceExtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 流程实例扩展信息表业务实现层
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessInstanceExtServiceImpl extends SuperServiceImpl<ProcessInstanceExtMapper, ProcessInstanceExt> implements ProcessInstanceExtService {
}
