package com.note.config;

import com.note.component.MyLocalResolver;
import com.note.filter.MyFilter;
import com.note.interceptor.LoginInterceptor;
import com.note.listener.MyListener;
import com.note.servlet.HelloServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @className: MySpringMvcConfigurer
 * @description: mvc自定义配置
 * @author: lgp
 * @date: 2020/10/12 20:17
 * @version: 1.0
 */
@Configuration
public class MySpringMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("main/login");
                registry.addViewController("/login.html").setViewName("main/login");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginInterceptor())
                        // 拦截所有请求
                        .addPathPatterns("/**")
                        // 排除不拦截
                        .excludePathPatterns("/","/login.html","/login","/bill/*")
                        .excludePathPatterns("/user/*")
                        .excludePathPatterns("/css/*","/img/*","/js/*");


            }
        };
    }

    /**
     * 区域解析器
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocalResolver();
    }

    /**
     * 注册自定义Servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean helloServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new HelloServlet(),"/hello");
        // 设置servlet参数
        bean.setLoadOnStartup(1);
        return bean;
    }

    /**
     * 自定义Filter
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        // 设置自定义filter
        bean.setFilter(new MyFilter());
        // 过滤请求
        bean.setUrlPatterns(Arrays.asList("/hello"));
        return bean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean(new MyListener());
        return bean;
    }
}
