package com.wemirr.framework.security.client.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * OAuth2 异常格式化
 *
 * @author Levin
 */
@Slf4j
public class Auth2ExceptionSerializer extends StdSerializer<Auth2Exception> {
    public Auth2ExceptionSerializer() {
        super(Auth2Exception.class);
    }

    @Override
    public void serialize(Auth2Exception value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("code", value.getCode());
        gen.writeStringField("message", value.getMessage());
        gen.writeObjectField("timestamp", System.currentTimeMillis());
        gen.writeBooleanField("successful", false);
        gen.writeEndObject();
    }
}
