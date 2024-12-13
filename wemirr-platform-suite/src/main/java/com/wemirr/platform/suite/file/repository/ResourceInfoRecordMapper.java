package com.wemirr.platform.suite.file.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.suite.file.domain.entity.ResourceInfoRecord;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author xiao1
 * @date 2024-12
 */
@Repository
public interface ResourceInfoRecordMapper extends SuperMapper<ResourceInfoRecord> {

    @Update("update t_file_record set original_filename=#{originName} where id=#{id}")
    void rename(Long id, String originName);
}
