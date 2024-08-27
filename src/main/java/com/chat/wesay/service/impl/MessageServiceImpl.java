package com.chat.wesay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chat.wesay.pojo.Message;
import com.chat.wesay.pojo.Result;
import com.chat.wesay.pojo.UnreadMessage;
import com.chat.wesay.service.MessageService;
import com.chat.wesay.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author abcde
* @description 针对表【message】的数据库操作Service实现
* @createDate 2024-08-06 00:32:03
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{
    @Autowired
    UnreadMessageServiceImpl unreadMessageService;
    public List<Message> getHistory(Long userId, Long friendId){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .and(wrapper -> wrapper
                        .eq(Message::getSenderId, userId)
                        .eq(Message::getReceiverId, friendId)
                        .or()
                        .eq(Message::getSenderId, friendId)
                        .eq(Message::getReceiverId, userId)
                )
                .orderByAsc(Message::getCreateTime);
        return list(queryWrapper);
    }

    public List<Message> getHistoryGroup(Long groupId) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Message::getReceiverId,groupId)
                .orderByAsc(Message::getCreateTime);
        return list(queryWrapper);
    }

    public Map<Long,Long> unreadCount(Long userId){
       QueryWrapper<Message> queryWrapper=new QueryWrapper<>();
       queryWrapper.lambda()
               .and(wrapper -> wrapper
                       .eq(Message::getReceiverId,userId)
                       .eq(Message::getIsRead,0)
               );
        List<Message> list = list(queryWrapper);

        Map<Long, Long> unreadFriend = list.stream()
                .collect(Collectors.groupingBy(Message::getSenderId, Collectors.counting()));
        Map<Long, Long> unreadGroup = unreadMessageService.getUnreadCount(userId);

        Map<Long,Long> unreadMap=new HashMap<>();

        unreadMap.putAll(unreadFriend);
        unreadMap.putAll(unreadGroup);

        return unreadMap;
    }

    public Boolean updateIsRead(Long receiverId,Long senderId){
        LambdaUpdateWrapper<Message> uw=new LambdaUpdateWrapper<>();
        uw.set(Message::getIsRead,1)
                .eq(Message::getReceiverId,receiverId)
                .eq(Message::getSenderId,senderId)
                .eq(Message::getIsRead,0);
        boolean update = update(uw);
        return update;
    }
}




