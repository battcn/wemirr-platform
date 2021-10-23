package com.wemirr.framework.boot.remote;


import com.google.common.base.Objects;
import com.wemirr.framework.commons.annotation.remote.Remote;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 封装 Remote 注解中解析出来的参数
 * <p>
 * 必须重写该类的 equals() 和 hashCode() 便于Map操作
 *
 * @author Levin
 */
@Data
@NoArgsConstructor
@ToString
public class LoadKey {

    /**
     * 执行查询任务的类
     */
    private String bean;

    public LoadKey(Remote rf) {
        this.bean = rf.bean();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoadKey that = (LoadKey) o;
        return Objects.equal(bean, that.bean);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bean);
    }
}
