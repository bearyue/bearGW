package com.jiaxincloud.gw.bear.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jiaxincloud.gw.lib.config.Config;
import com.jiaxincloud.gw.lib.config.ConfigItem;
import com.jiaxincloud.gw.lib.server.WebServer;

/**
 * 项目访问配置工具类
 * 
 * @author huangjs<br/>
 * @version 1.0<br/>
 * @email: huangjs@pci-suntektech.com<br/>
 * @datetime: 2015-09-16 <br/>
 */
public class AppConfigure {
    
    private static final Logger logger = LoggerFactory.getLogger(AppConfigure.class);

	/**
	 * 获取应用配置
	 * 
	 * @return
	 */
	public static Config getConfig() {
		Config config = WebServer.getInstance().getConfigService().getConfig();
		if (config == null) {
			logger.warn("can not get config, please check web.xml(you must have com.suntek.xserver.lib.context.AppContextListener)");
		}
		return config;
	}

	/**
	 * 获取应用配置
	 * 
	 * @param key
	 * @return
	 */
	public static String getConfigItem(String key) {
		Config conf = getConfig();
		for (ConfigItem item : conf.getConfItems()) {
			if (item.getKey().equals(key)) {
				return item.getValue();
			}
		}
		return null;
	}

}
