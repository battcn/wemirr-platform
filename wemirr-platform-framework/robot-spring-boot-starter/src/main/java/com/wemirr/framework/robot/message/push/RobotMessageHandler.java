
package com.wemirr.framework.robot.message.push;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.robot.emums.NotifyType;

import java.util.Map;

/**
 * 发送消息接口
 *
 * @author Levin
 */
public interface RobotMessageHandler {

    /**
     * 消息通知
     *
     * @param message 消息内容
     */
    String notify(String message);


    String getUrl();

    /**
     * 通知类型
     *
     * @return 通知类型
     */
    NotifyType notifyType();

    default String request(Map<String, Object> body) {
        return HttpUtil.post(this.getUrl(), JSON.toJSONString(body));
    }


}
