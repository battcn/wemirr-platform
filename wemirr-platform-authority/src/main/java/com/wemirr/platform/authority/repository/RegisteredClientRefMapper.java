package com.wemirr.platform.authority.repository;

import com.wemirr.framework.db.mybatis.SuperMapper;
import com.wemirr.platform.authority.domain.entity.baseinfo.RegisteredClientRef;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface RegisteredClientRefMapper extends SuperMapper<RegisteredClientRef> {


    /**
     * 根据ID 删除
     *
     * @param id id
     */
    @Delete("delete from oauth2_registered_client where id = #{id}")
    void removeById(@Param("id") String id);
}