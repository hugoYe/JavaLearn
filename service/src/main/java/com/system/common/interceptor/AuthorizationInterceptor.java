package com.system.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        /*
         * 1、登录验证：先判断用户是否登录，
         *     （1）如果未登录，check本次请求请求是否有NoLogin注解：如果有，则放行通过，否则抛出异常；
         *     （2）如果已经登录，将visitor设置进context，随后再进行权限验证
         * 2、权限验证：不管用户是否登录，都需要做权限验证，步骤如下：
         *     （1）check本次请求请求的方法是否有NoAuth注解，如果有，则放行；如果没有，则进行后续操作（2）
         *     （2）判断当前用户是否是超级管理员，如果是，则放行通过；否则进行权限验证，有权限则放行，没有则拦截
         */

//        throw new BizException("common.system.user.not.login");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
