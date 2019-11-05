package com.p2pInternetloan.sys.controller;

import com.p2pInternetloan.base.utils.JwtSession;
import com.p2pInternetloan.base.utils.PageUtils;
import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.base.utils.R;
import com.p2pInternetloan.sys.entity.Menu;
import com.p2pInternetloan.sys.entity.Permission;
import com.p2pInternetloan.sys.service.PermissionService;
import com.p2pInternetloan.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
     *
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
        return permissionService.queryUserMenu(JwtSession.getCurrentUserId());
//        return permissionService.queryUserMenu(1);
    }


    /**
     * 分页查询
     *
     * @param  params 请求参数集
     * @return 结果集封装对象
     */
    @GetMapping("queryPager")
    public PageUtils queryPager(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<Permission> list = permissionService.queryPager(query);
        return new PageUtils(list, query.getTotal());
    }


    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return R
     */
    @PostMapping("add")
    public  R add(Permission permission) {
        return R.update(permissionService.insert(permission));
    }



    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return R
     */
    @PostMapping("update")
    public R update(Permission permission) {
        return R.update(permissionService.update(permission));
    }

    /**
     * 删除数据
     *
     * @param perId 主键
     * @return R
     */
    @PostMapping("del")
    public  R del(Integer perId) {
        return R.update(permissionService.deleteById(perId));
    }


    /**
     *这是获取菜单的方法
     * @return
     */
    @GetMapping("getRoleModules")
    public List<Menu> getRoleModules(){

        return this.permissionService.queryModules();
    }
    /**
     * 根据角色id找到所有对应的权限id
     * @param roleId 角色Id
     * @return
     */
    @GetMapping("queryIdByRole")
    public List<Integer> queryIdByRole(Integer roleId){
        return this.permissionService.queryIdByRole(roleId);
    }
}