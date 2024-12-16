package com.wemirr.platform.suite.file.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.suite.file.domain.entity.FileStorageSetting;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author xiao1
 * @date 2024-12
 */
@Repository
public interface FileStorageSettingMapper extends SuperMapper<FileStorageSetting> {



    @Update("update t_file_storage_setting set enable_storage = 0 where tenant_id=#{tenantId} and platform !=  #{platform}")
    void updateConfigStatus(@Param("tenantId") Long tenantId, @Param("platform") String platform);
}
