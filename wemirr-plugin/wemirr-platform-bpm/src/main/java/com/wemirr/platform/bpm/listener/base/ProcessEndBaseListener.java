package com.wemirr.platform.bpm.listener.base;

import cn.hutool.extra.spring.SpringUtil;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.bpm.domain.entity.ProcessInstanceExt;
import com.wemirr.platform.bpm.domain.enums.ProcInstStatus;
import com.wemirr.platform.bpm.repository.ProcessInstanceExtMapper;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.time.Instant;


/**
 * 流程实例结束 目前正常结束会执行此监听器  作废流程不会
 *
 * @author Levin
 */
@Slf4j
@Component
public class ProcessEndBaseListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {
        var processInstanceExtMapper = SpringUtil.getBean(ProcessInstanceExtMapper.class);
        var wrapper = Wraps.<ProcessInstanceExt>lbQ().eq(ProcessInstanceExt::getProcInstId, execution.getProcessInstanceId());
        ProcInstStatus status = execution.isCanceled() ? ProcInstStatus.CANCEL : ProcInstStatus.DONE;
        log.debug("审批结果通知 - [processInstanceId - {}]", execution.getProcessInstanceId());
        processInstanceExtMapper.update(ProcessInstanceExt.builder().procInstEndTime(Instant.now()).procInstStatus(status).build(), wrapper);
    }
}
