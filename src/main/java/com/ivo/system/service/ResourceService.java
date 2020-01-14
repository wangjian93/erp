package com.ivo.system.service;

import com.ivo.system.entity.Resource;

import java.util.List;

/**
 * 菜单按钮等资源业务
 * @author wj
 * @version 1.0
 */
public interface ResourceService {

    /**
     * 根据id获取resource
     * @param id resource id
     * @return Resource
     */
    Resource getResource(Long id);


    /**
     * 获取所有resource
     * @return List<Resource>
     */
    List<Resource> getAllResource();

    /**
     * 添加resource
     * @param resource resource
     */
    void addResource(Resource resource);

    /**
     * 删除resource
     * @param id resource id
     */
    void deleteResource(long id);

    /**
     * 修改resource
     * @param resource resource
     */
    void modifyResource(Resource resource);
}
