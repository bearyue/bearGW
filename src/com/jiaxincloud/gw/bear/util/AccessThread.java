package com.jiaxincloud.gw.bear.util;

import java.util.concurrent.ExecutorService;

import com.jiaxincloud.gw.lib.utils.thread.JXExecutorUtil;

/**
 * 线程池执行
 *
 */
public class AccessThread {

    private static ExecutorService threadPool =
            JXExecutorUtil.newFixedThreadPool("CALLCENTER_DELIVERY", 10);

    /**
     * 执行线程
     * 
     * @param runnable
     */
    public static void executeThread(Runnable runnable) {
        threadPool.execute(runnable);
    }

}
