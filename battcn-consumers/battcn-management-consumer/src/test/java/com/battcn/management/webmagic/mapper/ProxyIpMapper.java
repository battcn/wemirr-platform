package com.battcn.management.webmagic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.battcn.management.webmagic.entity.ProxyIp;

@Mapper
public interface ProxyIpMapper {

    @Insert("insert into ip_pool (`ip`,`port`) values (#{ip},#{port})")
    void insert(ProxyIp proxyIp);
    
    @Select("select * from ip_pool where id = #{id}")
    public ProxyIp findProxyIpById(int id);

    @Select("select * from ip_pool")
    public List<ProxyIp> findAllProxies();
}