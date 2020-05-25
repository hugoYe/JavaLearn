package com.system.common.interceptor;

import com.system.business.adv.customer.dao.CustomerDao;
import com.system.business.adv.customer.domain.CustomerDomain;
import com.system.business.user.dao.UserDao;
import com.system.business.user.domain.UserDomain;
import com.system.common.annotation.NoLogin;
import com.system.common.constants.YesNoEnum;
import com.system.common.utils.Jwtutils;
import com.system.exception.BizException;
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

    @Autowired
    CustomerDao customerDao;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(NoLogin.class)) {
            return true;
        }

        String userId = Jwtutils.verifyToken(httpServletRequest, httpServletResponse);
        if (userId.startsWith("wy")) {
            UserDomain user = userDao.findByUserIdAndIsDeleted(userId, YesNoEnum.NO.getValue());
            if (null == user) {
                throw new BizException("user.not.exist");
            }
        } else {
            CustomerDomain customer = customerDao.findByCustIdAndIsDeleted(userId, YesNoEnum.NO.getValue());
            if (null == customer) {
                throw new BizException("customer.not.exist");
            }
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
