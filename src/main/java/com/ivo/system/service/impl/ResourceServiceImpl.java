package com.ivo.system.service.impl;

import com.ivo.common.enums.ResultEnum;
import com.ivo.common.exception.ResultException;
import com.ivo.system.entity.Resource;
import com.ivo.system.repository.ResourceRepository;
import com.ivo.system.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wj
 * @version 1.0
 */
@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;

    @Autowired
    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Resource getResource(Long id) {
        return resourceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Resource> getAllResource() {
        return resourceRepository.findAll();
    }

    @Override
    public void addResource(Resource resource) {
        log.info("添加Resource" + resource.getTitle());
        resourceRepository.save(resource);
    }

    @Override
    public void deleteResource(long id) {
        log.info("删除Resource" + id);
        Resource resource = getResource(id);
        if(resource == null) {
            throw new ResultException(ResultEnum.RESOURCE_NOT_FOUND);
        }
        resourceRepository.delete(resource);
    }

    @Override
    public void modifyResource(Resource resource) {
        log.info("修改Resource" + resource.getId());
        if(resource.getId()== null || getResource(resource.getId()) == null) {
            throw new ResultException(ResultEnum.RESOURCE_NOT_FOUND);
        }
        resourceRepository.save(resource);
    }
}
