package com.battcn.system.provider.mapper;

import com.battcn.framework.mybatis.mapper.BaseMapper;
import com.battcn.system.pojo.po.ProxyPool;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Mapper
@Repository
public interface ProxyPoolMapper extends BaseMapper<ProxyPool> {

    /**
     * 批量添加
     *
     * @param proxies 代理池
     */
    void batchInsertProxies(List<ProxyPool> proxies);

}