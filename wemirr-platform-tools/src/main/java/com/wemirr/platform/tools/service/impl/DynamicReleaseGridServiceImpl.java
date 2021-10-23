package com.wemirr.platform.tools.service.impl;

import com.wemirr.framework.db.mybatis.SuperServiceImpl;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseGrid;
import com.wemirr.platform.tools.mapper.DynamicReleaseGridMapper;
import com.wemirr.platform.tools.service.DynamicReleaseGridService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class DynamicReleaseGridServiceImpl extends SuperServiceImpl<DynamicReleaseGridMapper, DynamicReleaseGrid> implements DynamicReleaseGridService {
}
