package com.ivo.system.controller.validator;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Resource 数据效验
 * @author wj
 * @version 1.0
 */
@Data
public class ResourceValid {

    private Long pid;

    @NotEmpty(message = "资源名称不能为空或null")
    private String title;

    private String url = "";

    @NotEmpty(message = "权限标识不能为空或null")
    private String permission;

    private String icon = "";

    @NotNull(message = "资源类型不能为空")
    @Range(min=0, max=2, message = "资源类型不准确")
    private Byte type;

    private Integer sort;
}
