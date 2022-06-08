package com.mck.activiti.common.schedule;

import java.util.concurrent.ScheduledFuture;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public final class ScheduledTask {

    // 被volatile关键字修饰的变量，如果值发生了变更，其他线程立马可见，避免出现脏读的现象
    volatile ScheduledFuture<?> future;

    /**
     * @Description 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
