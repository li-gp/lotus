package com.note.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @className: LoginController
 * @description: 登录
 * @author: lgp
 * @date: 2020/10/12 20:12
 * @version: 1.0
 */
@Controller
public class LoginController {

    @RequestMapping("/login1")
    public String login (HttpSession session){
        session.setAttribute("loginUser","mr");
        System.out.println(".....11111");
        return "success";
    }
}
