package com.mck.activiti.module.flow.model.vo;

import com.mck.activiti.module.flow.model.entity.VacationOrder;
import lombok.Data;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Data
public class VacationOrderVo extends VacationOrder {

    private String orderNo;

    private String userName;

    private String processId;

    private String typeName;
}
