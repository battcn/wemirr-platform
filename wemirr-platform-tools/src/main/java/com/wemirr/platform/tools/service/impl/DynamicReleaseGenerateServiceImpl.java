package com.wemirr.platform.tools.service.impl;

import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.platform.tools.domain.DynamicReleaseDragSetting;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDrag;
import com.wemirr.platform.tools.mapper.DynamicReleaseDragMapper;
import com.wemirr.platform.tools.service.DynamicReleaseGenerateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DynamicReleaseGenerateServiceImpl implements DynamicReleaseGenerateService {

    private final DynamicReleaseDragMapper dynamicReleaseDragMapper;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void add(DynamicReleaseDragSetting req) {
        final DynamicReleaseDrag drag = this.dynamicReleaseDragMapper.selectById(req.getDragId());
        if (drag == null) {
            throw CheckedException.notFound("请先创建表单记录");
        }
        final String setting = JSON.toJSONString(req);
        DynamicReleaseDrag dynamicReleaseDrag = new DynamicReleaseDrag();
        dynamicReleaseDrag.setId(req.getDragId());
        dynamicReleaseDrag.setSetting(setting);
        this.dynamicReleaseDragMapper.updateById(dynamicReleaseDrag);
        final String tableName = drag.getTableName();
        stringRedisTemplate.delete(tableName);
        stringRedisTemplate.opsForValue().set(tableName, JSON.toJSONString(req));
    }
}
