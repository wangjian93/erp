package com.ivo.system.service;


import com.ivo.system.entity.Role;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Set;

/**
 * 角色业务
 * @author wj
 * @version 1.0
 */
public interface RoleService {

    /**
     * 角色名获取Role
     * @param name 角色名
     * @return Role
     */
    Role getRole(String name);

    /**
     * 添加角色
     * @param role 角色
     */
    void addRole(Role role);

    /**
     * 删除角色
     * @param name 角色名
     */
    void deleteRole(String name);

    /**
     * 根据查询实例分页查询角色
     * @param page 页数
     * @param size 分页大小
     * @param example 动态条件
     * @param sort 排序
     * @return Page<Role>
     */
    Page<Role> getPageUsers(int page, int size, Example<Role> example, Sort sort);

    /**
     * 角色授权
     * @param name 角色名
     * @param resourceIds 资源ID
     */
    void authorization(String name, Set<Long> resourceIds);

}
