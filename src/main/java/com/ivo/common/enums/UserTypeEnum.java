package com.ivo.common.enums;

import lombok.Getter;

/**
 * 用户的账号类型枚举
 * @author wj
 * @version 1.0
 */
@Getter
public enum  UserTypeEnum {

    EMPLOYEE((byte)1, "员工用户"),
    SYSTEM((byte)1, "系统用户");

    private Byte code;
    private String message;

    UserTypeEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
