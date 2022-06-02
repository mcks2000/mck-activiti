package com.mck.activiti.common.util;

import com.mck.activiti.model.vo.MenuVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 菜单工具类
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public class TreeUtil {

    public static List<MenuVo> toTree(List<MenuVo> treeList, Integer pid) {
        List<MenuVo> retList = new ArrayList<MenuVo>();
        for (MenuVo parent : treeList) {
            if (pid.equals(parent.getPid())) {
                retList.add(findChildren(parent, treeList));
            }
        }
        return retList;
    }

    private static MenuVo findChildren(MenuVo parent, List<MenuVo> treeList) {
        for (MenuVo child : treeList) {
            if (parent.getId().equals(child.getPid())) {
                if (parent.getChild() == null) {
                    parent.setChild(new ArrayList<>());
                }
                parent.getChild().add(findChildren(child, treeList));
            }
        }
        return parent;
    }
}
