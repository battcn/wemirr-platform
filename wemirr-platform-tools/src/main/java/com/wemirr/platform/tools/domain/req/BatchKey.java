package com.wemirr.platform.tools.domain.req;

import lombok.Data;

import java.util.List;

/**
 * @param <T> 泛型
 * @author Levin
 */
@Data
public class BatchKey<T> {

    private List<T> ids;

}
