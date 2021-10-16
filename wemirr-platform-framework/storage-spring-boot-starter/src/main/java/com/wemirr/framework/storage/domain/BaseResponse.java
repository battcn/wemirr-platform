package com.wemirr.framework.storage.domain;


import lombok.Data;

/**
 * 响应结果
 *
 * @author Levin
 */
@Data
public class BaseResponse implements java.io.Serializable {

    private static final long serialVersionUID = -498222912510624959L;

    protected boolean successful;
    protected String message;

}
