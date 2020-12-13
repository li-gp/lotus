package com.note.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lgp
 * @Date: 2020/9/22 18:08
 * @Version: 1.0
 */
@Configuration
public class DruidConfig {
    @ConfigurationProperties(
            prefix = "spring.datasource"
    )
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    /**
     * 配置一个druid监控
     * 1。配置servlet
     * 2。配置过滤器
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // 设置初始化参数
        Map<String,String> initMap = new HashMap<>(4);
        initMap.put(StatViewServlet.PARAM_NAME_USERNAME,"root");
        initMap.put(StatViewServlet.PARAM_NAME_PASSWORD,"root");
        initMap.put(StatViewServlet.PARAM_NAME_ALLOW,"");
        initMap.put(StatViewServlet.PARAM_NAME_DENY,"192.168.10.1");
        bean.setInitParameters(initMap);
        return bean;
    }

    /**
     * 配置Filter
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        // 设置初始化参数
        Map<String,String> initMap = new HashMap<>(1);
        initMap.put(WebStatFilter.PARAM_NAME_EXCLUSIONS,"*.js,*.css,/druid/*");
        bean.setInitParameters(initMap);
        // 设置拦截请求
        bean.setUrlPatterns(Collections.singletonList("/*"));
        return bean;
    }

}
