package com.wemirr.platform.authority.repository;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.framework.db.mybatisplus.intercept.data.DataPermission;
import com.wemirr.framework.db.mybatisplus.wrap.query.LbqWrapper;
import com.wemirr.platform.authority.domain.entity.baseinfo.User;
import com.wemirr.platform.authority.domain.resp.UserResp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
     * 查询用户
     *
     * @param username 用户名
     * @param tenantId 租户ID
     * @return 查询结果
     */
    @InterceptorIgnore(tenantLine = "true")
    @Select("select * from t_user where username = #{username} and tenant_id = #{tenantId}")
    User selectUserByTenantId(@Param("username") String username, @Param("tenantId") Long tenantId);

    /**
     * 带数据权限用户列表
     *
     * @param dataPermission dataScopeAspectJExpressionPointcut
     * @return 用户
     */
    List<User> list(DataPermission dataPermission);

    /**
     * 删除指定租户用户数据
     *
     * @param tenantId 租户ID
     */
    @InterceptorIgnore(tenantLine = "true")
    @Delete("delete from t_user where tenant_id = #{tenantId}")
    void deleteByTenantId(@Param("tenantId") Long tenantId);

    /**
     * 查询指定租户用户信息
     *
     * @param tenantId 租户ID
     * @return 查询结果
     */
    @InterceptorIgnore(tenantLine = "true")
    @Select("select * from t_user where tenant_id = #{tenantId}")
    List<User> selectByTenantId(@Param("tenantId") Long tenantId);
}
