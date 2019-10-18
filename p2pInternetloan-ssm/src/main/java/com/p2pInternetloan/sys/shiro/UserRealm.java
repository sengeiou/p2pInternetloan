package com.p2pInternetloan.sys.shiro;

import com.p2pInternetloan.sys.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: shiro的配置类
 * @Author: cpc
 * @Date: 2019-10-17 15:52
 * @Version: V1.0
 */
public class UserRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return null;
    }

//    /**
//     *用户授权（获取权限）
//     * @param principalCollection
//     * @return
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        Long userId = ShiroUtils.getUserId();
//        MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
//
//        Set<String> perms = menuService.listPerms(userId);
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringPermissions(perms);
//        return info;
//    }
//
//    /**
//     * 用户认证（登录）
//     * @param token
//     * @return
//     * @throws AuthenticationException
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        System.out.println("开始身份认证");
//        //获取用户名
//        String username = token.getPrincipal().toString();
//        //获取密码
//        String password = token.getCredentials().toString();
//        ShiroUser user = shiroUserService.queryByName(username);
//        //        拿到数据库中的用户信息，放入token凭证中，用于controler进行对比
//        AuthenticationInfo info = new SimpleAuthenticationInfo(
//                user.getUsername(),
//                user.getPassword(),
//                ByteSource.Util.bytes(user.getSalt()),
//                this.getName()
//        );
//
//        return info;
//    }
}
