package com.ivo.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ivo.common.constant.ValidStatusConst;
import com.ivo.common.model.EntityModel;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 * @author wj
 * @version 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "sys_role")
@Where(clause = ValidStatusConst.NOT_DELETE_SQL)
@SQLDelete(sql = "update sys_role " + ValidStatusConst.SLICE_Delete_For_Role_SQL)
public class Role extends EntityModel {

    /**
     * 角色名
     */
    @Id
    @NonNull
    private String name;

    /**
     * 名称
     */
    @NonNull
    private String title;

    /**
     * 角色拥有的菜单资源
     */
    @ManyToMany
    @JoinTable(name = "sys_role_resource",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    @JsonIgnoreProperties(value = {"roles"})
    private Set<Resource> resources = new HashSet<>(0);

    /**
     * 拥有该角色的用户
     */
    @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties(value = {"roles"})
    private Set<User> users = new HashSet<>(0);


}
