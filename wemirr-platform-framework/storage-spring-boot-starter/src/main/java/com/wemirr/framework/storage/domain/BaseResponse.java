package com.wemirr.framework.storage.domain;


import lombok.Data;

/**
 * 响应结果
 *
 * @author Levin
 */
@Data
public class BaseResponse implements java.io.Serializable {


    protected boolean successful;
    protected String message;

}
