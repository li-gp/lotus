package com.note.mapper;

import com.note.entities.User;
import org.springframework.stereotype.Repository;

/**
 * @className: UserMapper
 * @description: 用户信息Mapper
 * @author: lgp
 * @date: 2020/11/15 16:46
 * @version: 1.0
 */
@Repository
public interface UserMapper {
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int addUser(User user);

    User getByUsername(String username);

    int updateUser(User user);

    User getByTel(String tel);

    User getByEmail(String email);
}
