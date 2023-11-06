package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.i18n.core.I18nRedisTemplate;
import com.wemirr.framework.i18n.domain.I18nMessage;
import com.wemirr.platform.authority.repository.common.I18nDataMapper;
import com.wemirr.platform.authority.service.I18nDataService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class I18nDataServiceImpl implements I18nDataService {

    private final I18nDataMapper i18nDataMapper;
    private final I18nRedisTemplate i18nRedisTemplate;

    @PostConstruct
    public void init() {
        List<I18nMessage> messages = i18nDataMapper.selectList(Wraps.lbQ()).stream().map(x -> BeanUtil.toBean(x, I18nMessage.class)).toList();
        log.debug("从数据库加载国际化数据 - {}", JSON.toJSONString(messages));
        i18nRedisTemplate.loadI18nMessage(messages);
    }


}
