package com.wemirr.platform.tools.service.impl;

import com.wemirr.framework.db.mybatis.SuperServiceImpl;
import com.wemirr.platform.tools.domain.entity.FileEntity;
import com.wemirr.platform.tools.mapper.FileMapper;
import com.wemirr.platform.tools.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class FileServiceImpl extends SuperServiceImpl<FileMapper, FileEntity> implements FileService {

}
