package com._520it.crm.web.interceptor;

import com._520it.crm.domain.Employee;
import com._520it.crm.util.PermissionUtil;
import com._520it.crm.util.UserContext;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        //-----------------为当前线程绑定request对象------------------
        UserContext.set(httpServletRequest);

        //-----------------登录拦截-------------------
        // 从session中获取用户信息
        Employee employee = (Employee) httpServletRequest.getSession().getAttribute(UserContext.USER_IN_SESSION);
        if (employee == null) {
            // 拦截请求并重定向到登录页面
            httpServletResponse.sendRedirect("/login.jsp");
            // 记得return false，否则程序还是会往下走，经过拦截器达到目标Controller
            return false;
        }

        //-----------------权限校验-------------------
        if (handler instanceof HandlerMethod) {
            HandlerMethod methodObj = (HandlerMethod) handler;
            String function = methodObj.getBean().getClass().getName() + ":" + methodObj.getMethod().getName();
            // 判断当前用户是否有权限访问这个方法
            boolean flag = PermissionUtil.checkPermission(function);
            if (flag) {
                return true;
            } else {

                if (methodObj.getMethod().isAnnotationPresent(ResponseBody.class)) {
                    //如果是ajax
                    httpServletResponse.sendRedirect("/noPermission.json");//重定向是两次请求，个人觉得可以直接本次请求返回json数据
                } else {
                    //如果是页面，重定向到noPermission.jsp
                    httpServletResponse.sendRedirect("/noPermission.jsp");
                }

                return false;
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
