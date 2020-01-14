package com.ivo.system.controller;

import com.ivo.common.constant.ViewMapperConst;
import com.ivo.common.result.Result;
import com.ivo.common.utils.BaseTreeObj;
import com.ivo.common.utils.ResultUtil;
import com.ivo.common.utils.TreeUtil;
import com.ivo.system.controller.validator.ResourceValid;
import com.ivo.system.entity.Resource;
import com.ivo.system.service.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单资源管理
 * @author wj
 * @version 1.0
 */
@Controller
@RequestMapping("/system/resource")
public class ResourceController {

    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 跳转到页面
     */
    @GetMapping("/index")
    public String index() {
        return ViewMapperConst.view_system_resource;
    }

    /**
     * 获取菜单资源数据
     */
    @GetMapping("/getResources")
    @ResponseBody
    public Result getResources() {
        return ResultUtil.success(resourceService.getAllResource());
    }

    /**
     * 删除菜单资源
     * @param id resourceId
     */
    @DeleteMapping("/deleteResource")
    @ResponseBody
    public Result deleteResource(long id) {
        resourceService.deleteResource(id);
        return ResultUtil.success();
    }

    /**
     * 添加菜单资源
     */
    @PostMapping("/addResource")
    @ResponseBody
    public Result addResource(@Validated ResourceValid resourceValid) {
        Resource resource = new Resource();
        BeanUtils.copyProperties(resourceValid, resource);
        resourceService.addResource(resource);
        return ResultUtil.success();
    }

    /**
     * 修改菜单资源
     */
    @PostMapping("/modifyResource")
    @ResponseBody
    public Result modifyResource(@RequestParam long id, @Validated ResourceValid resourceValid) {
        Resource resource = resourceService.getResource(id);
        BeanUtils.copyProperties(resourceValid, resource);
        resourceService.modifyResource(resource);
        return ResultUtil.success();
    }

    /**
     * 获取菜单资源树
     */
    @GetMapping("/getResourceTree")
    @ResponseBody
    public List<BaseTreeObj> getResourceTree() {
        List<Resource> resourceList = resourceService.getAllResource();

        List<BaseTreeObj> baseTreeObjList = new ArrayList<>();
        resourceList.forEach(resource -> {
            BaseTreeObj treeObj = new BaseTreeObj();
            treeObj.setId(resource.getId().toString());
            treeObj.setParentId(resource.getPid()==null ? null : resource.getPid().toString());
            treeObj.setName(resource.getTitle());
            baseTreeObjList.add(treeObj);
        });

        return TreeUtil.list2TreeConverter(baseTreeObjList, null);
    }


}
