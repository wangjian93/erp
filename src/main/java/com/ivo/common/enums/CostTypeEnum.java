package com.ivo.common.enums;

import lombok.Getter;

/**
 * 成本类型枚举
 * @author wj
 * @version 1.0
 */
@Getter
public enum CostTypeEnum {

    DirectMaterial("DirectMaterial", "直接材料成本"),
    Jig("Jig", "治工具费用"),
    Verification("Verification", "验证费用"),
    Production("Production", "生产费用"),
    ReworkScrap("ReworkScrap", "重工报废"),
    PersonnelSalary("PersonnelSalary", "研发人员薪资"),
    Travel("Travel", "差旅费"),
    RMA("RMA", "RMA费用"),
    OBA("OBA", "OBA费用");

    private String type;
    private String description;

    CostTypeEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }

}
