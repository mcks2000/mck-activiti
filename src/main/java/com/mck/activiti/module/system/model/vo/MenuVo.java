package com.mck.activiti.module.system.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Description: 菜单实体类
 * @Author: mck
 * @Date: 2020/5/28 20:12
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MenuVo {

    private Long id;

    private Long pid;

    private String title;

    private String icon;

    private String href;

    private String target;

    private List<MenuVo> child;
}
