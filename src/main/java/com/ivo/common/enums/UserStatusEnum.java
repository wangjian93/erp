package com.ivo.common.enums;

import lombok.Getter;

/**
 * 用户账号状态枚举
 * @author wj
 * @version 1.0
 */
@Getter
public enum UserStatusEnum {

    DISABLED((byte) 0, "冻结"),
    UN_DISABLED((byte)1, "正常");

    private Byte code;
    private String message;

    UserStatusEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
