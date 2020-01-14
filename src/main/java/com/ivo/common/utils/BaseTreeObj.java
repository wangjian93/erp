package com.ivo.common.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 基层树形结构实体类
 * 必备属性:id,parentId,childsList
 * @author wj
 * @version 1.0
 */
@Data
public class BaseTreeObj implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String parentId;
    private String name;
    private List<BaseTreeObj> children;
}
