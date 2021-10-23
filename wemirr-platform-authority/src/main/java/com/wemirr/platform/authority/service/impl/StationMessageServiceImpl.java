package com.wemirr.platform.authority.service.impl;

import com.wemirr.framework.db.mybatis.SuperServiceImpl;
import com.wemirr.platform.authority.domain.entity.message.StationMessage;
import com.wemirr.platform.authority.repository.StationMessageMapper;
import com.wemirr.platform.authority.service.StationMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StationMessageServiceImpl extends SuperServiceImpl<StationMessageMapper, StationMessage> implements StationMessageService {


}
