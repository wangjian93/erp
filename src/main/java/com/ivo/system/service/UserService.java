package com.ivo.system.service;

import com.ivo.system.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Set;

/**
 * 用户业务
 * @author wj
 * @version 1.0
 */
public interface UserService {

    /**
     * 添加用户
     * @param user 用户
     */
    void addUser(User user);

    /**
     * 删除用户
     * @param userid 用户ID
     */
    void deleteUser(String userid);

    /**
     * 冻结用户
     * @param userid 用户ID
     */
    void frozenUser(String userid);

    /**
     * 根据userid获取用户
     * @param userid 用户ID
     * @return user
     */
    User getUser(String userid);

    /**
     * 根据查询实例分页查询用户
     * @param page 页数
     * @param size 每页大小
     * @param example 动态查询条件
     * @param sort 排序
     * @return Page<User>
     */
    Page<User> getPageUsers(int page, int size, Example<User> example, Sort sort);

    /**
     * 给用户分配角色
     * @param userid 用户ID
     * @param roleNames 角色名
     */
    void assignRoles(String userid, Set<String> roleNames);

    /**
     * 获取活用角色
     */
    Set<String> getRoles(String userid);

    /**
     * 获取用户权限
     * @param userid 用户ID
     * @return Set<String>
     */
    Set<String> getPermissions(String userid);

    /**
     * 修改密码
     * @param userid 用户ID
     * @param newPassword 新密码
     */
    void modifyPassword(String userid, String newPassword);

    /**
     * 验证密码
     * @param userid 用户ID
     * @param password 密码
     * @return boolean
     */
    boolean authentications(String userid, String password);

    /**
     * 判断用户是否被冻结不能登录
     * @return
     */
    boolean isFrozen(String userid);

}
