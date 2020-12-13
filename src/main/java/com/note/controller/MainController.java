package com.note.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: MainController
 * @description: 首页
 * @author: lgp
 * @date: 2020/10/12 20:28
 * @version: 1.0
 */
@Controller
public class MainController {

    @RequestMapping("/main")
    public String mainPage() {
        return "main/login";
    }
}
