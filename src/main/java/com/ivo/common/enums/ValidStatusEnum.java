package com.ivo.common.enums;

import com.ivo.common.constant.ValidStatusConst;
import lombok.Getter;

/**
 * 数据库字段状态有效性枚举
 * @author wj
 * @version 1.0
 */
@Getter
public enum ValidStatusEnum {

    VALID(ValidStatusConst.VALID, "有效"),
    DELETE(ValidStatusConst.DELETE, "删除");

    private Byte code;
    private String message;

    ValidStatusEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
