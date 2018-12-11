package com.system.common.interceptor;

import com.system.business.user.dao.UserDao;
import com.system.business.user.domain.UserDomain;
import com.system.common.annotation.NoLogin;
import com.system.common.constants.YesNoEnum;
import com.system.common.utils.Jwtutils;
import com.system.exception.BizException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(NoLogin.class)) {
            return true;
        }

        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            httpServletResponse.setStatus(401);
            throw new BizException("user.not.login", "401");
        }

        // 获取 token 中的 user id
        Integer userId;

        try {
            Claims claims = Jwtutils.parseJWT(token);
            userId = claims.get("userId", Integer.class);
        } catch (ExpiredJwtException e) {
            throw new BizException("user.token.expires", "401");
        }

        UserDomain user = userDao.findByIdAndIsDeleted(userId, YesNoEnum.NO.getValue());
        if (null == user) {
            throw new BizException("user.not.exist");
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
