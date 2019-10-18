package com.p2pInternetloan.sys.controller;

import com.p2pInternetloan.sys.entity.RolePermission;
import com.p2pInternetloan.sys.service.RolePermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (RolePermission)表控制层
 *
 * @author makejava
 * @since 2019-10-17 16:33:34
 */
@RestController
@RequestMapping("rolePermission")
public class RolePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private RolePermissionService rolePermissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public RolePermission selectOne(Integer id) {
        return this.rolePermissionService.queryById(id);
    }

}