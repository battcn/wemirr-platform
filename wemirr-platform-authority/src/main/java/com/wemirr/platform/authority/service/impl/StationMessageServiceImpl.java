package com.wemirr.platform.authority.service.impl;

import com.wemirr.framework.db.mybatis.SuperServiceImpl;
import com.wemirr.platform.authority.domain.entity.message.SiteMessage;
import com.wemirr.platform.authority.repository.StationMessageMapper;
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
public class StationMessageServiceImpl extends SuperServiceImpl<StationMessageMapper, SiteMessage> implements SiteMessageService {


}
