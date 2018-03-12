package com.battcn.system.pojo.po;

import com.battcn.framework.mybatis.pojo.RecordEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Table(name = "t_sys_proxy_pool")
@Data
public class ProxyPool extends RecordEntity implements Serializable {

    private static final long serialVersionUID = -3699072211264713025L;

    private Integer id;
    private String ip;
    private Integer port;
    private String source;
    private boolean locked;

}