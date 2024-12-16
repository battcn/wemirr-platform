package com.wemirr.platform.suite.file.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.suite.file.domain.entity.FileStorageSetting;
import org.springframework.stereotype.Repository;

/**
 * @author xiao1
 * @since 2024-12
 */
@Repository
public interface FileStorageSettingMapper extends SuperMapper<FileStorageSetting> {

}
