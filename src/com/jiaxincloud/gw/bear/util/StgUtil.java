/**
 * Description：字符拼接工具类
 * 
 * @author huangjs<br/>
 * @version 1.0<br/>
 * @date 2015年9月19日
 * 
 */
package com.jiaxincloud.gw.bear.util;


/**
 * Description：字符拼接工具类
 * 
 * @author huangjs<br/>
 * @version 1.0<br/>
 * @date 2015年9月19日
 * 
 */
public class StgUtil {
	/**
	 * 历史key
	 * 
	 * @param master
	 * @param seq
	 * @return
	 */
	public static String getKey(String master, int seq) {
		return master + "_" + seq;
	}
	
	/**
	 * 添加appKey
	 * @param appKey
	 * @param master
	 * @return
	 */
	public static String getFontKey(String appKey, String master) {
		return appKey + "-" + master;
	}
	
	/**
	 * 添加appKey和seq
	 * @param appKey
	 * @param master
	 * @param seq
	 * @return
	 */
	public static String getFullKey(String appKey, String master, int seq) {
		return appKey + "-" + master + "_" + seq;
	}
	
	/**
	 * appKey
	 * @param orgName
	 * @param appName
	 * @return
	 */
	public static String getAppKey(String orgName, String appName) {
		return (orgName + "#" + appName).toUpperCase();
	}
	
	/**
	 * 解析app key
	 * @param key
	 * @return
	 */
	public static String parserAppKey(String key) {
		return key.split("-")[0].toUpperCase();
	}
	
	/**
	 * 字符串是否为空
	 * @param param
	 * @return
	 */
	public static boolean isBlank(String param) {
		if (param == null || param.isEmpty()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * TODAY key
	 * @param param
	 * @return
	 */
	public static String todayKey(String param) {
		return param + "_TODAY";
	}
	
	/**
	 * day key
	 * @param key
	 * @param seq
	 * @return
	 */
	public static String getDayKey(String key, int seq) {
		return key + "_DAY_" + seq;
	}
}
