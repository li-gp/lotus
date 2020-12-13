package com.note;

import com.note.entities.User;
import com.note.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootTest
class LotusApplicationTests {

    private UserMapper userMapper;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Test
    void contextLoads() {
        User user = userMapper.getByUsername("ff7");
        if (user!=null){
            System.out.println(user.getCreateDate());
        } else {
            System.out.println("xxxxxx");
        }

    }

}
