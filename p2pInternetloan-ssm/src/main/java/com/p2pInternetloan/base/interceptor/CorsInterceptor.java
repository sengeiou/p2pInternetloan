package com.p2pInternetloan.base.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 跨域过滤器
 * @Author: cpc
 * @Date: 2019-10-06 7:04
 * @Version: V1.0
 */
@Slf4j
public class CorsInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler) throws Exception {
        log.info("进入跨域过滤器");
        // Access-Control-Allow-Origin就是我们需要设置的域名
        // Access-Control-Allow-Headers跨域允许包含的头。
        // Access-Control-Allow-Methods是允许的请求方式
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");// *,任何域名
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");

        //允许客户端发送一个新的请求头
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, jwt, verificationJwt");
        //允许客户端处理一个新的响应头 切记如果是多个使用 , 隔开千万不要再setHeader以为能添加
        httpResponse.setHeader("Access-Control-Expose-Headers", "jwt,verificationJwt");
//        httpResponse.setHeader("Access-Control-Expose-Headers", "verificationJwt");

        //axios和ajax会发送两次请求，第一次提交方式为：option直接返回即可
        if("OPTIONS".equals(httpRequest.getMethod())) {
            return false;
        }
        return true;
    }
}