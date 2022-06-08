package com.mck.activiti.common.config.db;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.mck.activiti.common.util.DateFormatUtil;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author mck
 * @Description: 自定义填充公共字段
 * @Date 2022/5/31
 */
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    private final MybatisPlusAutoFillProperties autoFillProperties;

    public MybatisPlusMetaObjectHandler(MybatisPlusAutoFillProperties autoFillProperties) {
        this.autoFillProperties = autoFillProperties;
    }

    /**
     * 是否开启了插入填充
     */
    @Override
    public boolean openInsertFill() {
        return autoFillProperties.getEnableInsertFill();
    }

    /**
     * 是否开启了更新填充
     */
    @Override
    public boolean openUpdateFill() {
        return autoFillProperties.getEnableUpdateFill();
    }

    /**
     * 插入填充，字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName(autoFillProperties.getCreateTimeField(), DateFormatUtil.getCurrentDateTime(), metaObject);
        this.setFieldValByName(autoFillProperties.getDelFlagField(), 0,metaObject);
}

    /**
     * 更新填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(autoFillProperties.getUpdateTimeField(), DateFormatUtil.getCurrentDateTime(), metaObject);
    }
}