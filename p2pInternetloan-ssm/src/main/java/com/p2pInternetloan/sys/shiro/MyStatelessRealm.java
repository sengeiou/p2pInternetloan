package com.p2pInternetloan.sys.shiro;

import com.p2pInternetloan.base.constant.CommonConstant;
import com.p2pInternetloan.base.utils.RedisUtil;
import com.p2pInternetloan.base.utils.SpringContextUtils;
import com.p2pInternetloan.base.utils.oConvertUtils;
import com.p2pInternetloan.sys.entity.User;
import com.p2pInternetloan.sys.service.UserService;
import com.p2pInternetloan.sys.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 这是自定义  realm
 * @Author: cpc
 * @Date: 2019-10-18 20:18
 * @Version: V1.0
 */
@Slf4j
public class MyStatelessRealm extends AuthorizingRealm {

    @Autowired(required = false)
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("Realm处理登录（验证）");
        //获取到 token 对象
        String token = (String) authenticationToken.getCredentials();
        if (token == null) {
            log.info("————————身份认证失败——————————IP地址:  "+ oConvertUtils.getIpAddrByRequest(SpringContextUtils.getHttpServletRequest()));
            throw new AuthenticationException("token为空!");
        }
        //检验token是否有效
        User user = this.checkUserTokenIsEffect(token);
        //这是将用户名 token 啥的都放入ok
        return new SimpleAuthenticationInfo(user, token, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("————权限认证 [ roles、permissions]————");
        //用户实体类对象
        User user = null;
        String userName = null;
        if (principals != null) {
            //获取到用户对象
            user = (User) principals.getPrimaryPrincipal();
            //获取到用户名
            userName = user.getUserName();
        }
        //用来返回的封装对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 设置用户拥有的角色集合，比如“admin,test”
        Set<String> roleSet = userService.getRolesByUserId(user.getUserId());
        info.setRoles(roleSet);
        // 设置用户拥有的权限集合，比如“sys:role:add,sys:user:add”
        Set<String> permissionSet = userService.getPersByUserId(user.getUserId());
        info.addStringPermissions(permissionSet);

        System.out.println("-----------------------------------------测试测试测试");
        System.out.println(permissionSet.contains("sys:user:view"));
        System.out.println(permissionSet.contains("sys:role:view"));

        //返回就好了
        return info;
    }




    /**
     * 校验token的有效性
     *
     * @param token
     */
    public User checkUserTokenIsEffect(String token) throws AuthenticationException {
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtils.getUserName(token);
        String password = JwtUtils.getUserPassword(token);

        //判断用户名是否为空，如果为空就抛出异常
        if (username == null) {
            throw new AuthenticationException("token非法无效!");
        }

        // 查询用户信息
        User user =this.userService.queryByName(username);
        if (user == null) {
            //如果用户信息不存在就报错
            throw new AuthenticationException("用户不存在!");
        }
        // 这里是检验token
        if (!jwtTokenRefresh(token, username, password)) {
            throw new AuthenticationException("Token失效，请重新登录!");
        }
        // 判断用户状态
        if (user.getUserFlag() != 1) {
            throw new AuthenticationException("账号已被锁定,请联系管理员!");
        }
        //如果成功就返回user对象
        return user;
    }


    /**
     * JWTToken刷新生命周期 （解决用户一直在线操作，提供Token失效问题）
     * 1、登录成功后将用户的JWT生成的Token作为k、v存储到cache缓存里面(这时候k、v值一样)
     * 2、当该用户再次请求时，通过JWTFilter层层校验之后会进入到doGetAuthenticationInfo进行身份验证
     * 3、当该用户这次请求JWTToken值还在生命周期内，则会通过重新PUT的方式k、v都为Token值，缓存中的token值生命周期时间重新计算(这时候k、v值一样)
     * 4、当该用户这次请求jwt生成的token值已经超时，但该token对应cache中的k还是存在，则表示该用户一直在操作只是JWT的token失效了，程序会给token对应的k映射的v值重新生成JWTToken并覆盖v值，该缓存生命周期重新计算
     * 5、当该用户这次请求jwt在生成的token值已经超时，并在cache中不存在对应的k，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
     * 6、每次当返回为true情况下，都会给Response的Header中设置Authorization，该Authorization映射的v为cache对应的v值。
     * 7、注：当前端接收到Response的Header中的Authorization值会存储起来，作为以后请求token使用
     * 参考方案：https://blog.csdn.net/qq394829044/article/details/82763936
     *
     * @param userName
     * @param passWord
     * @return
     */
    public boolean jwtTokenRefresh(String token, String userName, String passWord) {
        //这是拿到redis 中对应的 jwt 令牌
        String cacheToken = String.valueOf(redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token));
        //判断是否存在
        if (oConvertUtils.isNotEmpty(cacheToken)) {
            // 校验token有效性
            if (JwtUtils.validateJwtToken(cacheToken) == null) {
                Map map = new HashMap();
                map.put("userName", userName);
                map.put("password", passWord);
                //这是搞到新的 jwt 令牌
                String newAuthorization = JwtUtils.createJwt(map, JwtUtils.JWT_WEB_TTL);
                //这是将新令牌放入 redis中
                redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, newAuthorization);
                // 这是设置过期时间
                redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtils.JWT_WEB_TTL / 1000);
            } else {
                //这是令牌有效的请求
                redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, cacheToken);
                //加事件
                redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtils.JWT_WEB_TTL / 1000);
            }
            //检验通过
            return true;
        }
        //可能是还没有放入到redis中这里就判断一下施工方
        //这是检验失败
        return false;
    }



}
