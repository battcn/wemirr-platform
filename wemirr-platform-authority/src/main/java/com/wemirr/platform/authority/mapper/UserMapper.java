package com.wemirr.platform.authority.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.boot.SuperMapper;
import com.wemirr.framework.database.mybatis.auth.DataScope;
import com.wemirr.framework.database.mybatis.conditions.query.LbqWrapper;
import com.wemirr.platform.authority.domain.entity.User;
import com.wemirr.platform.authority.domain.vo.UserResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
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
     * @param dataScope dataScope
     * @return 用户
     */
    List<User> list(DataScope dataScope);
}
