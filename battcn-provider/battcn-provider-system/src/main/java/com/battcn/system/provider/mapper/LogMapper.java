package com.battcn.system.provider.mapper;

import com.battcn.framework.mybatis.mapper.BaseMapper;
import com.battcn.system.pojo.po.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Mapper
@Repository
public interface LogMapper extends BaseMapper<Log> {

}