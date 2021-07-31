package com.wemirr.platform.authority.service.impl;

import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.platform.authority.domain.entity.baseinfo.OAuthClientDetails;
import com.wemirr.platform.authority.repository.OAuthClientDetailsMapper;
import com.wemirr.platform.authority.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl extends SuperServiceImpl<OAuthClientDetailsMapper, OAuthClientDetails> implements ApplicationService {


}
