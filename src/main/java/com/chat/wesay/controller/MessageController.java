package com.chat.wesay.controller;

import com.chat.wesay.constant.Code;
import com.chat.wesay.pojo.Message;
import com.chat.wesay.pojo.Result;
import com.chat.wesay.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/msg")
public class MessageController {
    @Autowired
    MessageServiceImpl messageService;
    @GetMapping("getHistory")
    public Result getHistory(@RequestParam Long userId,@RequestParam Long friendId){
        List<Message> history = messageService.getHistory(userId, friendId);
        return Result.Success(Code.GET_OK,history);
    }
    @GetMapping("getHistoryGroup")
    public Result getHistoryGroup(@RequestParam Long groupId){
        List<Message> history = messageService.getHistoryGroup(groupId);
        return Result.Success(Code.GET_OK,history);
    }

    @GetMapping("unread")
    public Result getUnreadCount(@RequestParam Long userId){
        Map<Long, Long> m = messageService.unreadCount(userId);
        return Result.Success(Code.GET_OK,m);
    }

    @PutMapping("read")
    public Result updateIsRead(@RequestBody Message ms){
        Boolean b = messageService.updateIsRead(ms.getReceiverId(), ms.getSenderId());
        return b?Result.Success(Code.UPDATE_OK,b):Result.Error(Code.UPDATE_ERR,"更新已读失败");
    }
}
