package com.wemirr.framework.commons.entity;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * 国家区域地址对象-树形结构
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Area implements java.io.Serializable {


    private static final long serialVersionUID = 6873716596871564410L;

    private Integer id;
    /**
     * 名称
     */
    protected String name;

    /**
     * 父ID
     */
    protected Integer parentId;

    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;


    private List<Area> children;

    /**
     * 初始化子类
     */
    public void initChildren() {
        if (getChildren() == null) {
            this.setChildren(Lists.newArrayList());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Area area = (Area) o;
        return id.equals(area.id) &&
                name.equals(area.name) &&
                parentId.equals(area.parentId) &&
                Objects.equals(longitude, area.longitude) &&
                Objects.equals(latitude, area.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId, longitude, latitude);
    }
}