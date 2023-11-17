package com.wemirr.framework.boot.response;

import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlInjectionUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.wemirr.framework.commons.exception.CheckedException;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * JSON 全局反序列化器
 *
 * @author Levin
 */
@JsonComponent
public class GlobalJsonDeserializer {

    /**
     * 字符串反序列化器
     * 过滤特殊字符，解决 XSS 攻击
     */
    public static class StringDeserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            final String value = jsonParser.getValueAsString();
            if (SqlInjectionUtils.check(value)) {
                throw CheckedException.badRequest("存在SQL注入风险,已拦截");
            }
            // 替换 HTML 标签
            return HtmlUtil.escape(value);
        }
    }
}
