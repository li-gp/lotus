package com.note.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: LoginInterceptor
 * @description: LoginInterceptor登录拦截器
 * @author: lgp
 * @date: 2020/10/12 20:07
 * @version: 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 调用目标方法之前调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        System.out.println(loginUser);
        if(loginUser != null) {
            // 已登录放行
            return true;
        }
        System.out.println("经过拦截器！！！！！！");
//        request.setAttribute("msg","用户未登录！");
//        request.getRequestDispatcher("/").forward(request,response);
        return true;
    }
}
