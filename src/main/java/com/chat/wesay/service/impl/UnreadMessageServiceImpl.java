package com.chat.wesay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chat.wesay.pojo.UnreadMessage;
import com.chat.wesay.service.UnreadMessageService;
import com.chat.wesay.mapper.UnreadMessageMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author abcde
* @description 针对表【unread_message】的数据库操作Service实现
* @createDate 2024-08-17 20:17:20
*/
@Service
public class UnreadMessageServiceImpl extends ServiceImpl<UnreadMessageMapper, UnreadMessage>
    implements UnreadMessageService{

    public Map<Long,Long> getUnreadCount(Long userId){
        LambdaQueryWrapper<UnreadMessage> lqw=new LambdaQueryWrapper<>();
        lqw.eq(UnreadMessage::getUserId,userId);
        List<UnreadMessage> list = list(lqw);
        Map<Long, Long> unreadMap = list.stream()
                .collect(Collectors.groupingBy(UnreadMessage::getGroupId, Collectors.counting()));
        return unreadMap;
    }

    public Boolean clearUnread(Long userId, Long groupId) {
        LambdaQueryWrapper<UnreadMessage> lq=new LambdaQueryWrapper<>();
        lq.eq(UnreadMessage::getUserId,userId)
                .eq(UnreadMessage::getGroupId,groupId);
        return remove(lq);
    }
}




