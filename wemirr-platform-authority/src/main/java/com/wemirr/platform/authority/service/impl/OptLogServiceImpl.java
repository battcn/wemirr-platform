package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.wemirr.framework.boot.log.AccessLogInfo;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.authority.domain.common.entity.OptLog;
import com.wemirr.platform.authority.repository.common.OptLogMapper;
import com.wemirr.platform.authority.service.OptLogService;
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

    private final OptLogMapper optLogMapper;

    @Override
    public void save(AccessLogInfo info) {
        DynamicDataSourceContextHolder.push(info.getDsKey());
        log.debug("[日志信息] - {}", JSON.toJSONString(info));
        this.optLogMapper.insert(BeanUtil.toBean(info, OptLog.class));
        DynamicDataSourceContextHolder.poll();
    }

}
