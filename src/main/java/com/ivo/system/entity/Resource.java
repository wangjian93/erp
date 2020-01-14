package com.ivo.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ivo.common.constant.ValidStatusConst;
import com.ivo.common.enums.ResourceTypeEnum;
import com.ivo.common.model.EntityModel;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 系统的菜单、按钮资源等抽象
 * @author wj
 * @version 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "sys_resource")
@Where(clause = ValidStatusConst.NOT_DELETE_SQL)
@SQLDelete(sql = "update sys_resource " + ValidStatusConst.SLICE_Delete_SQL)
public class Resource extends EntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 上级菜单
     */
    private Long pid;

    /**
     * 所有上级菜单 格式：[0],[2],[4]
     */
    private String pids;

    /**
     * 菜单名称
     */
    @NonNull
    private String title;

    /**
     * 访问URL
     */
    private String url;

    /**
     * 对应的权限标识
     */
    private String permission;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型
     */
    private Byte type = ResourceTypeEnum.MENU.getCode();

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 所属角色
     */
    @ManyToMany(mappedBy = "resources")
    @JsonIgnoreProperties(value = {"resources"})
    @JsonIgnore
    private Set<Role> roles = new HashSet<>(0);
}
