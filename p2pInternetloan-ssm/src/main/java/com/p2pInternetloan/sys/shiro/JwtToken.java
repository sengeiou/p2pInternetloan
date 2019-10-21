package com.p2pInternetloan.sys.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description: 自定义 jwt 令牌
 * @Author: cpc
 * @Date: 2019-10-19 11:56
 * @Version: V1.0
 */
public class JwtToken  implements AuthenticationToken {
    private static final long serialVersionUID = 1L;

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
