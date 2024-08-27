package com.chat.wesay;

import com.chat.wesay.pojo.User;
import com.chat.wesay.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class WesayApplicationTests {
    @Autowired
    UserServiceImpl userService;
    @Test
    public void test(){
        User user=new User();
        user.setUsername("1");
        user.setPassword("1");
        user.setAvatar("1");
        user.setStatus(0);
        user.setBirth(new Date());
        user.setGender(0);
        boolean save = userService.save(user);
    }

}
