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

    @Column(name = "created_time", insertable = false, updatable = false)
    private Date createdTime;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "last_modified_time", insertable = false, updatable = false)
    private Date lastModifiedTime;

    @Column(name = "last_modified_by")
    private Long lastModifiedBy;

    public RecordEntity() {
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
