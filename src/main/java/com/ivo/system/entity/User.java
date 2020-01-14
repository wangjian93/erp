package com.ivo.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ivo.common.constant.ValidStatusConst;
import com.ivo.common.enums.UserStatusEnum;
import com.ivo.common.enums.UserTypeEnum;
import com.ivo.common.model.EntityModel;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

/**
 * 用户账号实体类
 * @author wj
 * @version 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="sys_user")
@Where(clause = ValidStatusConst.NOT_DELETE_SQL)
@SQLDelete(sql = "update sys_user " + ValidStatusConst.SLICE_Delete_For_User_SQL)
public class User extends EntityModel {

    /**
     * 用户ID
     */
    @Id
    @NonNull
    private String userid;

    /**
     * 用户名
     */
    @NonNull
    private String username;

    /**
     * 用户密码
     */
    @JsonIgnore
    private String password;

    /**
     * 密码盐值
     */
    @JsonIgnore
    private String salt;

    /**
     * 头像
     */
    private String picture = "/images/user.png";

    /**
     * 用户类型
     */
    private Byte type = UserTypeEnum.SYSTEM.getCode();

    /**
     * 账户状态
     */
    private Byte status = UserStatusEnum.UN_DISABLED.getCode();

    /**
     * 拥有的角色
     */
    @ManyToMany
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnoreProperties(value = {"users"})
    private Set<Role> roles;
}
