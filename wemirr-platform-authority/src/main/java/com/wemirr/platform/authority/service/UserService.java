package com.wemirr.platform.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.framework.db.mybatisplus.intercept.data.DataPermission;
import com.wemirr.framework.db.mybatisplus.wrap.query.LbqWrapper;
import com.wemirr.platform.authority.domain.entity.baseinfo.User;
import com.wemirr.platform.authority.domain.req.UserSaveReq;
import com.wemirr.platform.authority.domain.resp.UserResp;

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
     * 添加用户
     *
     * @param dto 用户信息
     */
    void addUser(UserSaveReq dto);

    /**
     * 根据条件查询
     *
     * @param scope scope
     * @return 查询结果
     */
    List<User> list(DataPermission scope);

    /**
     * 数据权限 分页
     *
     * @param page    page
     * @param wrapper wrapper
     * @return 查询结果
     */
    IPage<UserResp> findPage(IPage<User> page, LbqWrapper<User> wrapper);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param orgPassword 原始密码
     * @param newPassword 新密码
     */
    void changePassword(Long userId, String orgPassword, String newPassword);

    /**
     * 根据ID删除用户
     *
     * @param id id
     */
    void deleteById(Long id);
}
