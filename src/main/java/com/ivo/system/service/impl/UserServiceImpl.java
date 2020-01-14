package com.ivo.system.service.impl;

import com.ivo.common.enums.ResultEnum;
import com.ivo.common.enums.UserStatusEnum;
import com.ivo.common.exception.ResultException;
import com.ivo.component.shiro.ShiroUtil;
import com.ivo.system.entity.Role;
import com.ivo.system.entity.User;
import com.ivo.system.repository.UserRepository;
import com.ivo.system.service.RoleService;
import com.ivo.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * 用户业务
 * @author wj
 * @version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public void addUser(User user) {
        log.info("添加用户" + user.getUserid());
        if(getUser(user.getUserid()) != null) {
            throw new ResultException(ResultEnum.USER_EXIST);
        }
        // 设置初始密码， 为"123456"
        String salt = ShiroUtil.getRandomSalt();
        String password = ShiroUtil.encrypt("123456", salt);
        user.setPassword(password);
        user.setSalt(salt);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String userid) {
        log.info("删除用户" + userid);
        User user = getUser(userid);
        if(user == null) {
            throw new ResultException(ResultEnum.USER_NOT_FOUND);
        }
        userRepository.delete(user);
    }

    @Override
    public void frozenUser(String userid) {
        log.info("冻结用户" + userid);
        User user = getUser(userid);
        if(user == null) {
            throw new ResultException(ResultEnum.USER_NOT_FOUND);
        }
        user.setStatus(UserStatusEnum.DISABLED.getCode());
        userRepository.save(user);
    }

    @Override
    public User getUser(String userid) {
        return userRepository.findById(userid).orElse(null);
    }

    @Override
    public Page<User> getPageUsers(int page, int size, Example<User> example, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(example, pageable);
    }

    @Override
    public void assignRoles(String userid, Set<String> roleNames) {
        log.info("用户分配角色" + userid);
        User user = getUser(userid);
        if(user == null) {
            throw new ResultException(ResultEnum.USER_NOT_FOUND);
        }
        Set<Role> roleList = new HashSet<>();
        roleNames.forEach(roleName -> {
            Role role = roleService.getRole(roleName);
            if(role == null) {
                throw new ResultException(ResultEnum.ROLE_NOT_FOUNT);
            }
            roleList.add(role);
        });
        user.setRoles(roleList);
        userRepository.save(user);
    }

    @Override
    public Set<String> getRoles(String userid) {
        User user = getUser(userid);
        if(user == null) {
            throw new ResultException(ResultEnum.USER_NOT_FOUND);
        }
        Set<String> roles = new HashSet<>();
        user.getRoles().forEach(role -> roles.add(role.getName()));
        return roles;
    }

    @Override
    public Set<String> getPermissions(String userid) {
        User user = getUser(userid);
        if(user == null) {
            throw new ResultException(ResultEnum.USER_NOT_FOUND);
        }
        Set<Role> roles = user.getRoles();
        Set<String> permissions = new HashSet<>();
        roles.forEach(role -> role.getResources().forEach(menu -> {
            permissions.add(menu.getPermission());
        }));
        return permissions;
    }

    @Override
    public void modifyPassword(String userid, String newPassword) {
        log.info("修改用户" + userid);
        User user = getUser(userid);
        if(user == null) {
            throw new ResultException(ResultEnum.USER_NOT_FOUND);
        }
        String salt = ShiroUtil.getRandomSalt();
        String password = ShiroUtil.encrypt(newPassword, salt);
        user.setPassword(password);
        user.setSalt(salt);
        userRepository.save(user);
    }

    @Override
    public boolean authentications(String userid, String password) {
        return true;
    }

    @Override
    public boolean isFrozen(String userid) {
        User user = getUser(userid);
        if(user == null) {
            throw new ResultException(ResultEnum.USER_NOT_FOUND);
        }
        return user.getStatus().equals(UserStatusEnum.DISABLED.getCode());
    }
}
