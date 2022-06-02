package com.mck.activiti.common.util;

import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public class CommonUtil {

    /**
     * 唯一单号生成
     *
     * @return
     */
    public static long genId() {
        return IdUtil.getSnowflake(0, 0).nextId();
    }

    /**
     * 匹配规则
     *
     * @param keywords
     * @param orderData
     * @return
     */
    public static boolean checkKeywordsTrue(String keywords, String orderData) {
        if (StringUtils.isEmpty(keywords)) {
            return true;
        }
        if (StringUtils.isEmpty(orderData)) {
            return false;
        }
        String[] keywordStr = keywords.split(",");
        if (ArrayUtils.contains(keywordStr, orderData)) {
            return true;
        }
        Pattern r = Pattern.compile(keywords);
        Matcher matcher = r.matcher(orderData);
        if (matcher.matches()) {
            return true;
        }
        //支持正则表达
        return false;
    }


}
