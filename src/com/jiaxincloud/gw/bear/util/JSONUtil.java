/**
 * Description
 * 
 * @author huangjs<br/>
 * @version 1.0<br/>
 * @date 2015年11月16日
 * 
 */
package com.jiaxincloud.gw.bear.util;

import com.alibaba.fastjson.JSON;

/**
 * Description:输出json格式数据
 * 
 * @author huangjs<br/>
 * @version 1.0<br/>
 * @date 2015年11月16日
 * 
 */
public class JSONUtil {

	public static String asString(Object obj) {
		if (null == obj) {
			return "null";
		}

		return JSON.toJSONString(obj);
	}

}
