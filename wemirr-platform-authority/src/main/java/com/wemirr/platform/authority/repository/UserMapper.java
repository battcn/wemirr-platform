package com.wemirr.platform.authority.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.configuration.dynamic.ann.TenantDS;
import com.wemirr.framework.db.mybatis.SuperMapper;
import com.wemirr.framework.db.mybatis.auth.DataScope;
import com.wemirr.framework.db.mybatis.conditions.query.LbqWrapper;
import com.wemirr.platform.authority.domain.entity.baseinfo.User;
import com.wemirr.platform.authority.domain.vo.UserResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@TenantDS
@Repository
public interface UserMapper extends SuperMapper<User> {


    /**
     * 分页查询用户
     *
     * @param page    page
     * @param wrapper wrapper
     * @return 查询结果
     */
    IPage<UserResp> findPage(IPage<User> page, LbqWrapper<User> wrapper);

    /**
     * 带数据权限用户列表
     *
     * @param dataScope dataScopeAspectJExpressionPointcut
     * @return 用户
     */
    List<User> list(DataScope dataScope);
}
