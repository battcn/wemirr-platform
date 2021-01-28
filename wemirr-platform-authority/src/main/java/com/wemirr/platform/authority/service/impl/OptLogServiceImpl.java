package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.wemirr.framework.boot.config.log.OptLogDTO;
import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.framework.boot.utils.RegionUtils;
import com.wemirr.platform.authority.domain.entity.log.OptLog;
import com.wemirr.platform.authority.mapper.OptLogMapper;
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
    public void save(OptLogDTO dto) {
        log.info("[日志信息] - {}", JSON.toJSONString(dto));
        final OptLog record = BeanUtil.toBean(dto, OptLog.class);
        record.setLocation(RegionUtils.getRegion(dto.getIp()));
        this.optLogMapper.insert(record);
    }

}
