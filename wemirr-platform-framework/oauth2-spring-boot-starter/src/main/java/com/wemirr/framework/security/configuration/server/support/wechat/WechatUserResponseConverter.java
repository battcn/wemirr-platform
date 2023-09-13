package com.wemirr.framework.security.configuration.server.support.wechat;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信用户信息响应转换器
 *
 * @author Levin
 */
public class WechatUserResponseConverter extends MappingJackson2HttpMessageConverter {

    public WechatUserResponseConverter() {
        List<MediaType> mediaTypes = new ArrayList<>(super.getSupportedMediaTypes());
        // 微信获取token时响应的类型为“text/plain”，这里特殊处理一下
        mediaTypes.add(MediaType.TEXT_PLAIN);
        super.setSupportedMediaTypes(mediaTypes);
    }

}
