package com.wemirr.framework.websocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.websocket.Session;
import java.util.Date;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebSocket implements java.io.Serializable {

    /**
     * 代表一个连接
     */
    private Session session;

    /**
     * 唯一标识
     */
    private String identifier;
    /**
     * 最后心跳时间
     */
    private Date lastHeart;

}
