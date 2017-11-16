/**
 * Description：日期工具类
 * 
 * @author huangjs<br/>
 * @version 1.0<br/>
 * @date 2016年2月14日
 * 
 */
package com.jiaxincloud.gw.bear.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.lang3.time.DateUtils;

import com.alibaba.fastjson.JSON;

/**
 * Description：日期工具类
 * 
 * @author huangjs<br/>
 * @version 1.0<br/>
 * @date 2016年2月14日
 * 
 */
public class DateUtil {
	
	/**
	 * 字符串时间格式转换成时间戳
	 */
	public static Long getTime(String time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = simpleDateFormat.parse(time);
		} catch (ParseException e) {
			System.out.println("parse time to date error, time is " + time);
		}
		return date.getTime();
	}
	
	/**
	 * 字符串时间格式转换成时间戳
	 */
	public static Long getTime(String pattern, String time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = simpleDateFormat.parse(time);
		} catch (ParseException e) {
			System.out.println("parse time to date error, time is " + time);
		}
		return date.getTime();
	}
	
	/**
	 * 根据日期构建与一个数组，今天为0，昨天为1，依次
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static Integer[] buildKeys(String startTime, String endTime) {
		Long startDate = getTime(startTime);
		Long endDate = getTime(endTime);
		Long today = getTodayZero();
		
		int diff = new Long((endDate - startDate) / 86400000).intValue();
		int start = new Long((today - endDate) / 86400000).intValue();
		Integer[] res = new Integer[diff];
		for (int i = 0; i < diff; i++) {
			res[i] = start + i;
		}
		return res;
	}
	
	/**
	 * 得到今天00:00:00时间戳
	 * @return
	 */
	public static Long getTodayZero() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTimeInMillis();
	}
	
	/**
	 * 取特定月的第一天0点
	 * @param monthDiff
	 * @return
	 */
	public static Long getMonthFirstZero(Integer diff) {
		Calendar cal = Calendar.getInstance();
		if (diff != null) {
			cal.set(Calendar.MONTH, diff);
		}
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTimeInMillis();
	}
	
	/**
	 * 取一个月最后一天
	 * @param diff
	 * @return
	 */
	public static Long getMonthLastZero(Integer diff) {
		Calendar cal = Calendar.getInstance();
		if (diff != null) {
			cal.set(Calendar.MONTH, diff);
		}
		
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTimeInMillis();
	}
	
	/**
	 * 取近12个月的key
	 * @return
	 */
	public static Map<Integer, Integer[]> buildKeyPerMonth() {
		Map<Integer, Integer[]> maps = new HashMap<Integer, Integer[]>();
		Long start = null;
		Long end = null;
		Long today = getTodayZero();
		for (int i = 0; i < 12; i++) {
			if (i == 0) {
				start = getMonthFirstZero(null);
				end = getTodayZero();
			} else if (i == 1) {
				start = getMonthFirstZero(-0);
				end = getMonthLastZero(-0);
			} else {
				start = getMonthFirstZero(-i + 1);
				end = getMonthLastZero(-i + 1);
			}
			
			int diff = new Long((end - start) / 86400000).intValue();
			int todayInt = new Long((today - end) / 86400000).intValue();
			if (diff > 0) {
				Integer[] res = new Integer[diff + 1];
				for (int n = 0; n <= diff; n++) {
					res[n] = n + todayInt;
				}
				maps.put(i, res);
			} else {
				maps.put(i, null);
			}
		}
		return maps;
	}
	
	public static String getStatisDay(String type, int diff) {
		FastDateFormat fdf = FastDateFormat.getInstance(type);
		return fdf.format(DateUtils.addDays(new Date(), diff));
	}
	
	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(buildKeyPerMonth()));
		System.out.println(JSON.toJSONString(buildKeys("2016-02-01", "2016-02-12")));
		System.out.println(JSON.toJSONString(getStatisDay("yyyyMMdd", -1)));
		System.out.println(JSON.toJSONString(getTime("yyyyMMddHHmmss", "20160803235959")));
	}
}
