package com.battcn.platform.util.json;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

public class JsonUtils extends JSON
{
	protected final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	/**
	 * @Description: 判断是否为一个健康的JSON字符串
	 * @param @param
	 *            json
	 * @param @return
	 *            参数
	 * @return boolean 返回类型
	 */
	public static boolean isGoodJson(String json)
	{
		try
		{
			if (StringUtils.isNotBlank(json))
			{
				JSON.parseObject(json);
			}
			return true;
		} catch (JSONException e)
		{
			return false;
		}
	}
}