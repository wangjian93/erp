package com.ivo.common.enums;

import lombok.Getter;

/**
 * @author wj
 * @version 1.0
 */
@Getter
public enum StageEnum {

    NPRB(1, "NPRB"),
    Design(2, "DESIGN"),
    EVT(3, "EVT"),
    DVT(4, "DVT"),
    PVT(5, "PVT");

    private int sort;
    private String stage;

    StageEnum(int sort, String stage) {
        this.sort = sort;
        this.stage = stage;
    }
}
