package com.mck.activiti.common.config.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;

/**
 * @Description: 日志打印
 * @Author: mck
 * @Date: 2022/5/31 10:17
 **/
@Slf4j
public class MyStdOutImpl implements Log {
    public MyStdOutImpl(String clazz) {
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        log.error(s);
        e.printStackTrace(System.err);
    }

    @Override
    public void error(String s) {
        log.error(s);
    }

    @Override
    public void debug(String s) {
        log.info(s);
    }

    @Override
    public void trace(String s) {
//        log.trace(s);
    }

    @Override
    public void warn(String s) {
        log.warn(s);
    }
}
