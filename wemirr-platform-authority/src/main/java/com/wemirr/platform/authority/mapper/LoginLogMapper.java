package com.wemirr.platform.authority.mapper;

import com.wemirr.framework.boot.SuperMapper;
import com.wemirr.platform.authority.domain.entity.log.LoginLog;
import com.wemirr.platform.authority.domain.vo.LoginArea;
import com.wemirr.platform.authority.domain.vo.LoginRecord;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 查询登录记录
     *
     * @return 查询结果
     */
    List<LoginRecord> selectLoginRecord();

    /**
     * 查询登录区域
     *
     * @return 查询结果
     */
    List<LoginArea> selectLoginArea();

}
