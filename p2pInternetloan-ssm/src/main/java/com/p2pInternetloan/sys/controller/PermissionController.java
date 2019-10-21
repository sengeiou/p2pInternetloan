package com.p2pInternetloan.sys.controller;

import com.p2pInternetloan.sys.entity.Menu;
import com.p2pInternetloan.sys.service.PermissionService;
import com.p2pInternetloan.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (Permission)表控制层
 *
 * @author cpc
 * @since 2019-10-19 21:18:15
 */
@RestController
@Api(description ="权限&菜单 请求处理")
@RequestMapping("/sys/permission")
public class PermissionController {
    /**
     * 服务对象
     */
    @Resource
    private PermissionService permissionService;


    /**
     * 用户请求对象
     */
    @Resource
    private UserService userService;


    /**
     * 获取用户菜单页
     * @param request
     * @return
     */
    @ApiOperation(value = "获取用户菜单", notes = "这里会根据前台放在请求头中的 jwt 令牌来获取相关下拉菜单")
    @GetMapping("queryUserMenu")
    public List<Menu> queryUserMenu(HttpServletRequest request) {
//        String jwt = request.getHeader(CommonConstant.JWT_HEADER_KEY);
//        //获取用户id
//        Claims claims = JwtUtils.parseJwt(jwt);
//        String userName = (String) claims.get("userName");
//        return permissionService.queryUserMenu(userService.queryByName(userName).getUserId());
        return permissionService.queryUserMenu(1);
    }





}