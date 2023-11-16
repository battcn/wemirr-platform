package com.wemirr.platform.authority.repository.baseinfo;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.datascope.annotation.DataColumn;
import com.wemirr.framework.db.mybatisplus.datascope.annotation.DataScope;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.baseinfo.entity.User;
import com.wemirr.platform.authority.domain.baseinfo.req.UserPageReq;
import com.wemirr.platform.authority.domain.baseinfo.resp.UserResp;
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
     * @param page page
     * @param req  req
     * @return 查询结果
     */
    @DataScope(columns = @DataColumn(name = Entity.CREATE_USER_COLUMN))
    IPage<UserResp> findPage(@Param("page") IPage<User> page, @Param("req") UserPageReq req);

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
     * @return 用户
     */
    @DataScope(columns = @DataColumn(name = Entity.CREATE_USER_COLUMN))
    List<User> list();

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
