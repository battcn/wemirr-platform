package com.battcn.system.pojo.po;

import com.battcn.framework.mybatis.pojo.RecordEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Levin
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_sys_proxy_pool")
@Data
public class ProxyPool extends RecordEntity implements Serializable {

    private static final long serialVersionUID = -3699072211264713025L;

    private Integer id;
    private String host;
    private Integer port;
    private String anonymity;
    private String type;
    private String location;
    private String validateTime;
    private boolean locked;

}