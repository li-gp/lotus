package com.note.controller;

import com.note.entities.User;
import com.note.service.IUser;
import com.note.util.base.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @className: UserController
 * @description: 新建用户
 * @author: lgp
 * @date: 2020/11/15 16:25
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private IUser iUser;

    @Autowired
    public void setiUser(IUser iUser) { this.iUser = iUser; }

    /**
     * 事务测试用
     * @param user
     * @return
     */
    @GetMapping("/addUser")
    public Boolean addUser(User user) {
        Boolean f = false;
        f = iUser.addUser(user);
        return f;
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        System.out.println("....");
        return iUser.register(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return iUser.login(user.getUserName(), user.getPassWord());
    }

    @GetMapping("/info")
    public Result getUserInfo(@RequestParam String token) {
        return iUser.getUserInfo(token);
    }

    @PutMapping("/update")
    public Result updateUser(@RequestBody User user) {
        return iUser.update(user);
    }

    @GetMapping("/verify")
    public Result verify(HttpSession session, @RequestParam(value = "mode") String mode, @RequestParam(value = "tel") String tel, @RequestParam(value = "email") String email) {
        session.setAttribute("mode",mode);
        session.setAttribute("tel",tel);
        session.setAttribute("email",email);
//        Result result = iUser.verify(mode, tel, email);
//        Map<String,String> map = (HashMap<String, String>)result.getData();
//        session.setAttribute("verify",map.get("verifyNo"));
        return iUser.verify(mode, tel, email);
    }

    @GetMapping("/thirdLogin")
    public Result thirdLogin(HttpSession session, @RequestParam String verifyNo) {
        Result result = null;
        String verifyNo1 = (String)session.getAttribute("verifyNo");
        if (StringUtils.isBlank(verifyNo)) {
            result = Result.error("数据异常，请联系管理员");
        }
        if (verifyNo.equals(verifyNo1)) {
            String mode = (String)session.getAttribute("mode");
            String tel = (String)session.getAttribute("tel");
            String email = (String)session.getAttribute("email");
            result = iUser.thirdLogin(mode, tel, email);
        }
        return result;
    }
}
