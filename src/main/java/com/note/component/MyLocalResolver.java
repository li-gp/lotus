package com.note.component;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @className: MyLocalResolver
 * @description: 切换语言
 * @author: lgp
 * @date: 2020/10/15 20:15
 * @version: 1.0
 */
public class MyLocalResolver implements LocaleResolver
{
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        
        // 获取请求头信息，设置语言
        String l = httpServletRequest.getParameter("l");
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(l)) {
            String[] split =  l.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
