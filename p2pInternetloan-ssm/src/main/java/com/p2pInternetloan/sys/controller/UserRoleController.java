package com.p2pInternetloan.sys.controller;

import com.p2pInternetloan.sys.entity.UserRole;
import com.p2pInternetloan.sys.service.UserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserRole)表控制层
 *
 * @author makejava
 * @since 2019-10-17 16:33:34
 */
@RestController
@RequestMapping("userRole")
public class UserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private UserRoleService userRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserRole selectOne(Integer id) {
        return this.userRoleService.queryById(id);
    }



}