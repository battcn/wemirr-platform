package com.wemirr.platform.authority.service.impl;

import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.authority.domain.common.entity.SiteMessage;
import com.wemirr.platform.authority.repository.common.SiteMessageMapper;
import com.wemirr.platform.authority.service.SiteMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StationMessageServiceImpl extends SuperServiceImpl<SiteMessageMapper, SiteMessage> implements SiteMessageService {


}
