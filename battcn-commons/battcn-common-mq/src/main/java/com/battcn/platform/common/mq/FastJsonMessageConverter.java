package com.battcn.platform.common.mq;

import java.io.UnsupportedEncodingException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import com.alibaba.fastjson.JSON;

/**
 * @ClassName: FastJsonMessageConverter
 * @author 唐亚峰
 * @date 2016年10月31日
 */
public class FastJsonMessageConverter extends AbstractMessageConverter {

	public static final String DEFAULT_CHARSET = "UTF-8";

	private volatile String defaultCharset = DEFAULT_CHARSET;

	public FastJsonMessageConverter() {
		super();
	}

	public void setDefaultCharset(String defaultCharset) {
		this.defaultCharset = (defaultCharset != null) ? defaultCharset : DEFAULT_CHARSET;
	}

	public Object fromMessage(Message message) throws MessageConversionException {
		return null;
	}

	protected Message createMessage(Object objectToConvert, MessageProperties messageProperties)
			throws MessageConversionException {
		byte[] bytes = null;
		try {
			String jsonString = JSON.toJSONString(objectToConvert);// FastJson.toJson(objectToConvert);
			bytes = jsonString.getBytes(this.defaultCharset);
		} catch (UnsupportedEncodingException e) {
			throw new MessageConversionException("Failed to convert Message content", e);
		}
		messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
		messageProperties.setContentEncoding(this.defaultCharset);
		if (bytes != null) {
			messageProperties.setContentLength(bytes.length);
		}
		return new Message(bytes, messageProperties);
	}
}