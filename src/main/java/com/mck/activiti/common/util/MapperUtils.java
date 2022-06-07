package com.mck.activiti.common.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.annotation.Bean;

/**
 * @Description: 实体拷贝
 * @Author: mck
 * @Date: 2022/06/07 10:17
 **/
public class MapperUtils {

    private static final class MapperFactoryHolder {
        private static final MapperFactory INSTANCE = new DefaultMapperFactory.Builder().build();
    }

    @Bean
    private static MapperFactory mapperFactory(){
        return MapperFactoryHolder.INSTANCE;
    }

    /**
     * 获取 MapperFacade
     * @return
     */
    public static MapperFacade getMapper() {
        return mapperFactory().getMapperFacade();
    }

    /**
     * bean 转换
     * @param bean 需要转换的bean
     * @param zlazz 转换之后的类型bean
     * @param <T>
     * @return
     */
    public static <T> T beanConvert(Object bean,Class<T> zlazz){
        if (ObjectUtils.isEmpty(bean)){
            return null;
        }
        return MapperUtils.getMapper().map(bean, zlazz);
    }

}
