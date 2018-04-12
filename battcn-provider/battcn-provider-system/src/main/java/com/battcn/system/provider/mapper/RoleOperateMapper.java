package com.battcn.system.provider.mapper;

import com.battcn.framework.mybatis.mapper.BaseMapper;
import com.battcn.system.pojo.po.RoleOperate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 * @version 2.5.1
 * @since 2018-01-10
 */
@Mapper
@Repository
public interface RoleOperateMapper extends BaseMapper<RoleOperate> {

    /**
     * 批量保存 角色操作信息
     *
     * @param ops 数据集
     * @return 操作结果
     */
    int batchInsertRoleOperate(List<RoleOperate> ops);

}