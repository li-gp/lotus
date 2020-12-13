package com.note.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @className: MyFilter
 * @description: 自定义Filter
 * @author: lgp
 * @date: 2020/10/16 11:53
 * @version: 1.0
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter初始化");
    }

    @Override
    public void destroy() {
        System.out.println("filter销毁");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter处理完成");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
