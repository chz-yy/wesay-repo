package com.chat.wesay.controller;

import com.chat.wesay.constant.Code;
import com.chat.wesay.pojo.Result;
import com.chat.wesay.service.impl.UnreadMessageServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/unread")
public class UnreadMessageController {

    @Autowired
    UnreadMessageServiceImpl unreadMessageService;

    @DeleteMapping("clearUnread")
    public Result clearUnread(@RequestParam Long userId, @RequestParam Long groupId){
        Boolean b = unreadMessageService.clearUnread(userId, groupId);
        return b?Result.Success(Code.DELETE_OK,b):Result.Error(Code.DELETE_ERR,"清除未读失败");
    }
}
