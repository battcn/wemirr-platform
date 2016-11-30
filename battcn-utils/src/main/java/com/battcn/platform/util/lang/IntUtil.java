package com.battcn.platform.util.lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: IntUtil
 * @Description: 整数工具类
 * @author 唐亚峰
 * @date 2016年8月23日
 */
public class IntUtil
{
	static Logger logger = LoggerFactory.getLogger(IntUtil.class);

	public static Integer intVal(String str)
	{
		try
		{
			return Integer.parseInt(str.trim());
		} catch (NumberFormatException e)
		{
			logger.error("类型转换异常:" + e.getMessage() + "开始尝试正则匹配转换....");
			String regEx = "[^0-9]";
			Pattern pattern = Pattern.compile(regEx);
			Matcher matcher = pattern.matcher(str);
			return Integer.parseInt(matcher.replaceAll("").trim());
		}
	}
}
