package com.ivo.system.service.impl;

import com.ivo.common.enums.ResultEnum;
import com.ivo.common.exception.ResultException;
import com.ivo.system.entity.Resource;
import com.ivo.system.entity.Role;
import com.ivo.system.repository.RoleRepository;
import com.ivo.system.service.ResourceService;
import com.ivo.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wj
 * @version 1.0
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final ResourceService resourceService;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ResourceService resourceService) {
        this.roleRepository = roleRepository;
        this.resourceService = resourceService;
    }

    @Override
    public Role getRole(String name) {
        return roleRepository.findById(name).orElse(null);
    }

    @Override
    public void addRole(Role role) {
        log.info("添加角色" + role.getName());
        if(getRole(role.getName()) != null) {
            throw new ResultException(ResultEnum.ROLE_EXIST);
        }
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(String name) {
        log.info("删除角色" + name);
        Role role = getRole(name);
        if(role == null) {
            throw new ResultException(ResultEnum.ROLE_NOT_FOUNT);
        }
        roleRepository.delete(role);
    }

    @Override
    public Page<Role> getPageUsers(int page, int size, Example<Role> example, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return roleRepository.findAll(pageable);
    }

    @Override
    public void authorization(String name, Set<Long> resourceIds) {
        log.info("角色授权" + name);
        Role role = getRole(name);
        if(role == null) {
            throw new ResultException(ResultEnum.ROLE_NOT_FOUNT);
        }
        Set<Resource> resources = new HashSet<>();
        resourceIds.forEach(resourceId -> {
            Resource resource = resourceService.getResource(resourceId);
            if(resource == null) {
                throw new ResultException(ResultEnum.ROLE_NOT_FOUNT);
            }
            resources.add(resource);
        });
        role.setResources(resources);
        roleRepository.save(role);
    }
}
