package com.battcn.framework.mybatis.pojo;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类通用父类
 *
 * @author Levin
 */
public class RecordEntity implements Serializable {

    private static final long serialVersionUID = 7026470724648329551L;

    @Column(name = "created_time", insertable = false, updatable = false)
    private Date createTime;

    @Column(name = "last_modified_time", insertable = false, updatable = false)
    private Date lastModifiedTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
