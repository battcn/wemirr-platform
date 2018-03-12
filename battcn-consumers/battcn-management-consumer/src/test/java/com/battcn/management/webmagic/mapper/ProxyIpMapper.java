package com.battcn.management.webmagic.mapper;

import com.battcn.management.webmagic.entity.ProxyPool;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProxyIpMapper {

    @Insert("insert into ip_pool (`ip`,`port`) values (#{ip},#{port})")
    void insert(ProxyPool proxyPool);

    @Select("select * from ip_pool where id = #{id}")
    ProxyPool findProxyIpById(int id);

    @Select("select * from ip_pool")
    List<ProxyPool> findAllProxies();
}