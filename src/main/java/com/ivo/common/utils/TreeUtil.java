package com.ivo.common.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 获得树形结构的工具类
 * 实际使用,可将BaseTreeObj直接覆盖为指定类
 * @author wj
 * @version 1.0
 */
public class TreeUtil {
    /**
     * 获得指定节点下所有归档
     * @param list
     * @param parentId
     * @return
     */
    public static List<BaseTreeObj> list2TreeConverter(List<BaseTreeObj> list, String parentId) {
        List<BaseTreeObj> returnList = new ArrayList<>();

        for (BaseTreeObj res : list) {
            //判断对象是否为根节点
            if (res.getParentId() == null || res.getParentId().equals(parentId)) {
                //该节点为根节点,开始递归
                recursionFn(list, res); //通过递归为节点设置childList

                returnList.add(res);
            }
        }

        return returnList;
    }

    /**
     * 递归列表
     * 通过递归,给指定t节点设置childList
     * @param list
     * @param t
     */
    public static void recursionFn(List<BaseTreeObj> list, BaseTreeObj t) {
        //只能获取当前t节点的子节点集,并不是所有子节点集
        List<BaseTreeObj> childsList = getChildList(list, t);
        //设置他的子集对象集
        t.setChildren(childsList);

        if(childsList == null) {
            return;
        }
        //迭代子集对象集
        for (BaseTreeObj nextChild : childsList) { //遍历完,则退出递归

            //判断子集对象是否还有子节点
            if (!CollectionUtils.isEmpty(childsList)) {
                //有下一个子节点,继续递归
                recursionFn(list, nextChild);
            }
        }
    }

    /**
     * 获得指定节点下的所有子节点
     * @param list
     * @param t
     * @return
     */
    public static List<BaseTreeObj> getChildList(List<BaseTreeObj> list, BaseTreeObj t) {
        List<BaseTreeObj> childsList = new ArrayList<>();
        //遍历集合元素,如果元素的parentId==指定元素的id,则说明是该元素的子节点
        for (BaseTreeObj t1 : list) {
            if (t1.getParentId()!=null && t1.getParentId().equals(t.getId()) ) {
                childsList.add(t1);
            }
        }
        if(childsList.size() == 0) {
            return null;
        }
        return childsList;
    }

    /**
     * 判断是否还有下一个子节点
     * @param list
     * @param t
     */
    public static boolean hasChild(List<BaseTreeObj> list, BaseTreeObj t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
