package com.chat.wesay.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chat.wesay.constant.Code;
import com.chat.wesay.pojo.Result;
import com.chat.wesay.pojo.User;
import com.chat.wesay.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("login")
    public Result login(@RequestBody User user){
       User u=userService.login(user);
       if(u==null){
           return Result.Error(Code.GET_ERR,"用户不存在");
       }else {
           UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
           updateWrapper.set("last_login_time",new Date());
           userService.update(updateWrapper);
           return Result.Success(Code.GET_OK,u);
       }
    }
    @PostMapping("register")
    public Result register(@RequestBody User user){
        User u=userService.getUserByUserName(user);
        if(u==null){
            boolean save = userService.save(user);
            if(save){
                return Result.Success(Code.SAVE_OK,null);
            }else {
                return Result.Error(Code.SAVE_ERR,"注册失败");
            }
        }
        return Result.Error(Code.SAVE_ERR,"用户已存在");
    }

    @GetMapping("getUserByUserId")
    public Result getUserByUserId(@RequestParam Long userId){
        User u=userService.getById(userId);
        return u!=null?Result.Success(Code.GET_OK,u):Result.Error(Code.GET_ERR,"用户不存在");
    }

    @PutMapping("updateUserInfo")
    public Result updateUserInfo(@RequestBody User user){
        System.out.println(user);
        boolean b = userService.updateById(user);
        return b?Result.Success(Code.UPDATE_OK,b):Result.Error(Code.UPDATE_ERR,"用户信息更新失败");
    }

}
