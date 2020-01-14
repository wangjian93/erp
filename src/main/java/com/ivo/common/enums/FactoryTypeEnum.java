package com.ivo.common.enums;

import lombok.Getter;

/**
 * @author wj
 * @version 1.0
 */
@Getter
public enum FactoryTypeEnum {

    Array("Array", "Array"),
    Cell("Cell", "Cell"),
    Module("Module", "Modules");

    private String factory;
    private String description;

    FactoryTypeEnum(String factory, String description) {
        this.factory = factory;
        this.description = description;
    }
}
