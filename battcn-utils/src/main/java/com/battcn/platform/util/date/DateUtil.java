package com.battcn.platform.util.date;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: DateUtil
 * @Description: JAVA日期工具类
 * @author 唐亚峰
 * @date 2016年8月29日
 */
public class DateUtil {

	static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	public final static String shortFormat = "yyyyMMdd";
	public final static String longFormat = "yyyyMMddHHmmss";
	public final static String webFormat = "yyyy-MM-dd";
	public final static String timeFormat = "HHmmss";
	public final static String chineseDtFormat = "yyyy年MM月dd日";
	public final static String newFormat = "yyyy-MM-dd HH:mm:ss";
	public final static String noSecondFormat = "yyyy-MM-dd HH:mm";

	/**
	 * @Description 判断日期大小 <br/>
	 * @param d1
	 *            开始日期 <br/>
	 * @param d2
	 *            结束日期 <br/>
	 * @return boolean d1 < d2 ? true : false <br/>
	 */
	public static boolean compareDate(Date d1, Date d2) {
		try {
			return d1.before(d2);
		} catch (Exception exception) {
			logger.error("日期转换错误,异常{}", exception.getMessage());
		}
		return false;
	}

	/**
	 * @return 当天的时间格式化为"yyyy-MM-dd HH:mm:ss"
	 */
	public static String getDateString(Date date) {
		DateFormat df = getNewDateFormat(newFormat);

		return df.format(date);
	}

	/**
	 * @return 当天的时间格式化为"yyyy-MM-dd HH:mm:ss"
	 */
	public static Date getToDay() {
		Date date = new Date();
		return new Date(date.getTime());
	}

	public static DateFormat getNewDateFormat(String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);

		df.setLenient(false);
		return df;
	}

	/**
	 * @Description 判断日期大小 <br/>
	 * @param d1
	 *            开始日期 <br/>
	 * @param d2
	 *            结束日期 <br/>
	 */
	public static boolean compareDate(String d1, String d2) {
		return compareDate(d1, d2);
	}

	public static void main(String[] args) {

		System.out.println(getDateString(getToDay()));

	}

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");

	public static Date parse(String source) throws ParseException {
		if (source.length() <= dateTimeFormat.toPattern().length()
				&& source.length() >= dateTimeFormat.toPattern().length() - 5) {
			try {
				return dateTimeFormat.parse(source);
			} catch (ParseException ex) {
			}
		}
		if (source.length() <= dateFormat.toPattern().length()
				&& source.length() >= dateFormat.toPattern().length() - 2) {
			try {
				return dateFormat.parse(source);
			} catch (ParseException ex) {
			}
		}
		if (source.length() <= monthFormat.toPattern().length()
				&& source.length() >= monthFormat.toPattern().length() - 1) {
			try {
				return monthFormat.parse(source);
			} catch (ParseException ex) {
			}
		}
		return dateTimeFormat.parse(source);
	}

	/**
	 * Parse the Date using pattern "yyyy-MM-dd"
	 * 
	 * @param source
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String source) throws ParseException {
		return dateFormat.parse(source);
	}

	/**
	 * Format the Date using pattern "yyyy-MM-dd"
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * Parse the Date using pattern "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param source
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDateTime(String source) throws ParseException {
		return dateTimeFormat.parse(source);
	}

	/**
	 * Format the Date using pattern "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date) {
		return dateTimeFormat.format(date);
	}

	/**
	 * Parse the Date using pattern "yyyy-MM"
	 * 
	 * @param source
	 * @return
	 * @throws ParseException
	 */
	public static Date parseMonth(String source) throws ParseException {
		return monthFormat.parse(source);
	}

	/**
	 * Format the Date using pattern "yyyy-MM"
	 * 
	 * @param date
	 * @return
	 */
	public static String formatMonth(Date date) {
		return monthFormat.format(date);
	}

	/**
	 * 返回相应格式的当前时间
	 * 
	 * @param format
	 * @return
	 */
	public static String getNowDate(String format) {
		String timeStr = "";
		SimpleDateFormat sf = new SimpleDateFormat(format);
		timeStr = sf.format(new Date());

		return timeStr;
	}

	/**
	 * 得到几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 查询每个月的第一天
	 * 
	 * @param monthDifference
	 *            月份差，传入0，则为当月，-1为上一月，1为下一月
	 * @return
	 */
	public static Date getFirstDayEveryMonth(int monthDifference) {
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.add(Calendar.MONTH, monthDifference);
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,则为第一天
		return cal.getTime();
	}

	/**
	 * 查询每个月的最后一天
	 * 
	 * @param monthDifference
	 *            月份差，传入0，则为当月，-1为上一月，1为下一月
	 * @return
	 */
	public static Date getLastDayEveryMonth(int monthDifference) {
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.add(Calendar.MONTH, monthDifference);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	/**
	 * 
	 * @Description: 两个日期相加减小时
	 * @param @param
	 *            smdate
	 * @param @param
	 *            bdate
	 * @param @return
	 * @param @throws
	 *            ParseException
	 * @return int
	 * @throws @date
	 *             2016年7月26日
	 */
	public static String daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DecimalFormat df = new DecimalFormat("#######0.00");// 保留两位小数
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		Float between_days;
		if (time2 > time1) {
			between_days = (((float) time2 - (float) time1) / (1000 * 3600));
		} else {
			between_days = (((float) time1 - (float) time2) / (1000 * 3600));
		}
		// System.out.println(between_days);
		// return Integer.parseInt(String.valueOf(between_days));
		return df.format(between_days);
	}

	/**
	 * 两个时间之间相差距离多少天
	 * 
	 * @param one
	 *            时间参数 1：
	 * @param two
	 *            时间参数 2：
	 * @return 相差天数
	 */
	public static long getDistanceDays(String str1, String str2) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date one;
		Date two;
		long days = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			days = diff / (1000 * 60 * 60 * 24);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}

	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param str1
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param str2
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return long[] 返回值为：{天, 时, 分, 秒}
	 */
	public static long[] getDistanceTimes(String str1, String str2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date one;
		Date two;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long[] times = { day, hour, min, sec };
		return times;
	}

	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param str1
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param str2
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return String 返回值为：xx天xx小时xx分xx秒
	 */
	public static String getDistanceTime(String str1, String str2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date one;
		Date two;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return day + "天" + hour + "小时" + min + "分" + sec + "秒";
	}
}
