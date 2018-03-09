package com.battcn.framework.mybatis.pojo;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类通用父类
 *
 * @author fly
 */
public class RecordEntity implements Serializable {

    private static final long serialVersionUID = 7026470724648329551L;

    @Column(name = "gmt_create", insertable = false, updatable = false)
    private Date gmtCreate;

    @Column(name = "gmt_modified", insertable = false, updatable = false)
    private Date gmtModified;

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
