package com.wemirr.platform.authority.repository;

import com.wemirr.framework.boot.SuperMapper;
import com.wemirr.platform.authority.domain.entity.log.LoginLog;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 系统日志
 * </p>
 *
 * @author Levin
 * @since 2019-10-20
 */
@Repository
public interface LoginLogMapper extends SuperMapper<LoginLog> {


    /**
     * 统计 IP 数据
     *
     * @return 统计结果
     */
    @Select("SELECT count(DISTINCT ( ip )) FROM common_login_log")
    long countDistinctLoginIp();

}
