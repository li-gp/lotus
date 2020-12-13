package com.note.service;

import com.note.entities.User;
import com.note.util.base.Result;

/**
 * @className: IUser
 * @description: 用户
 * @author: lgp
 * @date: 2020/11/2 15:50
 * @version: 1.0
 */
public interface IUser {
    //hibernate创建表类型是myisam是非事务安全的，不支持事务回滚，使用hibernate自动创建表要指定表类型innodb来支持事务
    Boolean addUser(User user);

    Result login(String username, String password);

    Result register(User user);

    Result getUserInfo(String token);

    Result update(User user);

    Result verify(String mode, String tel, String email);

    Result thirdLogin(String mode, String tel, String email);
}
