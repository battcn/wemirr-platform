package com.wemirr.platform.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.framework.database.mybatis.auth.DataScope;
import com.wemirr.framework.database.mybatis.conditions.query.LbqWrapper;
import com.wemirr.platform.authority.domain.entity.baseinfo.User;
import com.wemirr.platform.authority.domain.vo.UserResp;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 账号
 * </p>
 *
 * @author Levin
 * @since 2019-07-03
 */
public interface UserService extends SuperService<User> {


    /**
     * 根据条件查询
     *
     * @param scope scope
     * @return 查询结果
     */
    List<User> list(DataScope scope);

    /**
     * 数据权限 分页
     *
     * @param page    page
     * @param wrapper wrapper
     * @return 查询结果
     */
    IPage<UserResp> findPage(IPage<User> page, LbqWrapper<User> wrapper);


}
