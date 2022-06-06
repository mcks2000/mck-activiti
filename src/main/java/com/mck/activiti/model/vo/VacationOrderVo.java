package com.mck.activiti.model.vo;

import com.mck.activiti.model.entity.VacationOrder;
import lombok.Data;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Data
public class VacationOrderVo extends VacationOrder {

    private String userName;

    private String flowId;

    private String typeName;
}
