package com.ivo.common.enums;

import lombok.Getter;

/**
 * 菜单类型枚举
 * @author wj
 * @version 1.0
 */
@Getter
public enum ResourceTypeEnum {

    BUTTON((byte)0, "按钮"),
    MENU((byte)1, "菜单"),
    OTHER((byte)2, "其他");

    private Byte code;

    private String message;

    ResourceTypeEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
