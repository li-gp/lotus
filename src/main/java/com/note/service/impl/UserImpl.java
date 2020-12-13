package com.note.service.impl;

import com.note.entities.User;
import com.note.mapper.UserMapper;
import com.note.service.IUser;
import com.note.util.JwtUtil;
import com.note.util.SendMessageUtil;
import com.note.util.base.Result;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * @className: UserImpl
 * @description: 创建用户实践类
 * @author: lgp
 * @date: 2020/11/15 16:22
 * @version: 1.0
 */
@Service
public class UserImpl implements IUser {

    private UserMapper userMapper;

    private JwtUtil jwtUtil;

    private SendMessageUtil sendMessageUtil;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    public void setSendMessageUtil(SendMessageUtil sendMessageUtil) {
        this.sendMessageUtil = sendMessageUtil;
    }

    /**
     * 事务测试用
     * @param user
     * @return
     */
    @Transactional
    @Override
    public Boolean addUser(User user) {
        Boolean flag = false;
        // 插入成功
//        user = new User(6,"dd6","ee6","ff6",36,"gg6",null);
//        int i = userMapper.addUser(user);
// 插入失败，回滚，全失败
//        user = new User(7,"ff7","aa7","bb7",123456,"hh7",null);
        int i = userMapper.addUser(user);
        if(i>0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public Result login(String username, String password) {
        Result error = Result.error("用户名或密码错误");
        if (StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return error;
        }

        User user = getByUsername(username);
        if (user==null){
            return error;
        }

        // 加密判断
        boolean b =new BCryptPasswordEncoder().matches(password,user.getPassWord());
        if(!b){
            return error;
        }

        String jwt = jwtUtil.createJwt(String.valueOf(user.getId()),user.getUserName(),true);

        Map<String,String> map = new HashMap<>(1);
        map.put("token",jwt);

        return Result.ok(map);
    }

    private User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    public Result register(User user) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        if (user==null || StringUtils.isBlank(user.getPassWord())){
            return Result.error("数据异常，请联系管理员");
        }
        String hashPass = bcryptPasswordEncoder.encode(user.getPassWord());
        user.setPassWord(hashPass);

        int i = userMapper.addUser(user);

        if (i==0){
            return Result.error("系统出错，请联系管理员");
        }

        String jwt = jwtUtil.createJwt(String.valueOf(user.getId()),user.getUserName(),true);

        Map<String,String> map = new HashMap<>(1);
        map.put("token",jwt);
        return Result.ok(map);
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @Override
    public Result getUserInfo(String token) {
        Claims claims = jwtUtil.parseJwt(token);

        if (claims==null || StringUtils.isBlank(claims.getSubject())) {
            return Result.error("获取用户令牌失败");
        }

        String username = claims.getSubject();

        User user = getByUsername(username);
        if (user==null){
            return Result.error("用户不存在");
        }

        user.setPassWord(null);

        return Result.ok(user);
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @Override
    public Result update(User user) {
        if(user==null) {
            return Result.error("数据错误，请联系管理员");
        }
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(user.getPassWord());
        user.setPassWord(hashPass);
        int i = userMapper.updateUser(user);

        if (i==0){
            return Result.error("系统出错，请联系管理员");
        }

        return Result.ok(null);
    }

    @Override
    public Result verify(String mode, String tel, String email) {
        Result result = null;
        if (StringUtils.isBlank(mode)){
            return Result.error("数据异常，请联系管理员");
        }
        String verifyNo = sendMessageUtil.getRandomCode(6);
        if ("tel".equals(mode)){
//            "疾风***", "d41d8cd98f**********", "159********", "验证码:" + getRandomCode(6)
            Integer resultCode = sendMessageUtil.send("ligp2020","d41d8cd98f00b204e980",tel,verifyNo);
            String msg = sendMessageUtil.getMessage(resultCode);
            if (resultCode>0){
                Map<String,String> map = new HashMap<String,String>(1);
                map.put("verifyNo",verifyNo);
                result = Result.ok(msg,map);
            } else {
                result = Result.error(msg);
            }
        }
        if ("email".equals(mode)) {
            String sendResult = sendMessageUtil.sendEmail("2323688845@qq.com",email,"验证码",verifyNo,"",false);
            if ("ok".equals(sendResult)) {
                HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
                HttpSession session = request.getSession();
                session.setAttribute("verifyNo",verifyNo);
                return Result.ok("发送成功");
            } else {
                return Result.ok("发送失败");
            }

        }
        return Result.error("数据异常，请联系管理员");
    }

    @Override
    public Result thirdLogin(String mode, String tel, String email) {
        Result result = null;
        if (StringUtils.isBlank(mode)) {
            result = Result.error("数据异常");
        }
        User user = null;
        if ("tel".equals(mode)){
            user = getByTel(tel);
            result =  getResult(user);
        }

        if ("email".equals(mode)){
            user = getByEmail(email);
            result =  getResult(user);
        }

        return result;
    }

    private Result getResult(User user) {
        Result result = Result.error("数据异常");
        if (user!=null) {
            String jwt = jwtUtil.createJwt(String.valueOf(user.getId()),user.getUserName(),true);
            if (jwt!=null){
                Map<String, String> map = new HashMap<>(1);
                map.put("token",jwt);
                result = Result.ok("登录成功",map);
            }
        }
        return result;
    }

    public User getByTel(String tel) {
        return userMapper.getByTel(tel);
    }

    public User getByEmail(String email) {
        return userMapper.getByEmail(email);
    }
}
