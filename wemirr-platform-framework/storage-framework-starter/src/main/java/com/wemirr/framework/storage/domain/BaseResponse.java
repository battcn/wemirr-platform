package com.wemirr.framework.storage.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应结果
 *
 * @author Levin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse implements java.io.Serializable {

    private static final long serialVersionUID = -498222912510624959L;

    private boolean successful;
    private String message;

}
