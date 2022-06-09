package com.mck.activiti.common.util;

import cn.hutool.core.util.ObjectUtil;
import com.mck.activiti.common.config.exception.BizException;


/**
 * @Description: 参数校验工具
 * @Author: mck
 * @Date: 2022/06/07 10:17
 **/
public class ParamAssertUtil {

    public static void isTrue(boolean param) {
        isTrue(param, "参数错误，不能为false");
    }

    public static void isTrue(boolean param, Object msgOrEnum) {
        if (!param) {
            notice(msgOrEnum);
        }
    }

    public static void isFalse(boolean param) {
        isFalse(param, "参数错误，不能为true");
    }

    public static void isFalse(boolean param, Object msgOrEnum) {
        if (param) {
            notice(msgOrEnum);
        }
    }

    public static <T> T isNull(T param) {
        return isNull(param, "参数错误，已存在数据");
    }

    public static <T> T isNull(T param, Object msgOrEnum) {
        if (ObjectUtil.isNotNull(param)) {
            notice(msgOrEnum);
        }
        return param;
    }

    public static <T> T notNull(T param) {
        return notNull(param, "参数错误，不能为空");
    }

    public static <T> T notNull(T param, Object msgOrEnum) {
        if (ObjectUtil.isNull(param)) {
            notice(msgOrEnum);
        }
        return param;
    }

    public static <T> T isEmpty(T param) {
        return isEmpty(param, "參數錯誤，已存在数据");
    }

    public static <T> T isEmpty(T param, Object msgOrEnum) {
        if (ObjectUtil.isNotEmpty(param)) {
            notice(msgOrEnum);
        }
        return param;
    }

    public static <T> T notEmpty(T param) {
        return notEmpty(param, "參數錯誤，不能空集");
    }

    public static <T> T notEmpty(T param, Object msgOrEnum) {
        if (ObjectUtil.isEmpty(param)) {
            notice(msgOrEnum);
        }
        return param;
    }

    /**
     * 通知异常，异常信息为：参数错误
     */
    public static void notice() {
        notice("参数错误");
    }

    /**
     * 通知异常
     */
    public static void notice(Object msgOrEnum) {
        throw new BizException(ObjectUtil.isNotNull(msgOrEnum) ? msgOrEnum.toString() : "参数错误");
    }
}
