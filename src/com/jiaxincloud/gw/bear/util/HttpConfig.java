package com.jiaxincloud.gw.bear.util;

import java.util.Map;

import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

/**
 * 
 * HTTP配置信息
 * 
 * @author YFB<br/>
 * @version 1.0<br/>
 * @date 2015年8月31日
 *
 */
public class HttpConfig {

    /**
     * 默认超时时间为30秒
     */
    public static final int DEFAULT_TIMEOUT = 30*1000;
    public static final String TIMEOUT_CONFIGURATION = "timeout";

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";

    public static DefaultHttpClient getDefaultHttpClient() {
        return getDefaultHttpClient(DEFAULT_TIMEOUT);
    }

    public static DefaultHttpClient getDefaultHttpClient(int paramInt) {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, paramInt);
        HttpConnectionParams.setSoTimeout(httpParams, 20000);
        HttpConnectionParams.setTcpNoDelay(httpParams, true);
        HttpProtocolParams.setUserAgent(httpParams, getUserAgent());
        DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
        httpClient.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
        httpClient.setReuseStrategy(new DefaultConnectionReuseStrategy());
        return httpClient;
    }

    private static String getUserAgent() {
        return CoreProtocolPNames.USER_AGENT;
    }

    /**
     * 获取自定义超时时间
     * 
     * @param params HTTP请求参数
     * @return timeout 超时时长
     */
    public static int getTimeout(Map<String, String> params) {
        int timeout = DEFAULT_TIMEOUT;
        if(params != null) {
            String value = params.get(TIMEOUT_CONFIGURATION);
            if(value != null) {
                timeout = Integer.parseInt(value);
                params.remove(TIMEOUT_CONFIGURATION);
            }
        }
        return timeout;
    }
}
