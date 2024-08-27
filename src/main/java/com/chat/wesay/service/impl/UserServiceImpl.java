package com.chat.wesay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chat.wesay.constant.Code;
import com.chat.wesay.exception.BusinessException;
import com.chat.wesay.exception.SystemException;
import com.chat.wesay.pojo.Result;
import com.chat.wesay.pojo.User;
import com.chat.wesay.service.UserService;
import com.chat.wesay.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author abcde
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-08-06 11:24:35
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    public User login(User user) {
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.allEq(Map.of("username", user.getUsername(), "password", user.getPassword()));
            User us = this.getOne(queryWrapper);
            return us;
        } catch (Exception e) {
            throw new SystemException();
        }
    }

    public User getUserByUserName(User user){
        try{
            QueryWrapper<User> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("username",user.getUsername());
            return getOne(queryWrapper);
        }catch (Exception e){
            throw new SystemException();
        }
    }
}




