package com.wemirr.framework.feign.plugin.decoder;

import com.wemirr.framework.commons.entity.Result;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class FeignResponseDecoder implements Decoder {


    private final SpringDecoder decoder;


    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        Method method = response.request().requestTemplate().methodMetadata().method();
        log.debug("feign decode response - {}", response);
        //如果Feign接口的返回值不是 Response{code:0,...} 结构类型，并且远程响应又是这个结构
        boolean notTheSame = method.getReturnType() != Result.class;
        if (notTheSame) {
            //构造一个这个结构类型
            Type newType = new ParameterizedType() {
                @Override
                public Type[] getActualTypeArguments() {
                    return new Type[]{type};
                }

                @Override
                public Type getRawType() {
                    return Result.class;
                }

                @Override
                public Type getOwnerType() {
                    return null;
                }
            };
            Result<?> result = (Result<?>) this.decoder.decode(response, newType);
            //只返回data
            return result.getData();
        }
        return this.decoder.decode(response, type);
    }
}