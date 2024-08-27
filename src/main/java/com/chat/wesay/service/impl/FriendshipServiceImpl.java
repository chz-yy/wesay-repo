package com.chat.wesay.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chat.wesay.constant.Code;
import com.chat.wesay.exception.BusinessException;
import com.chat.wesay.exception.SystemException;
import com.chat.wesay.mapper.FriendshipMapper;
import com.chat.wesay.pojo.Friendship;
import com.chat.wesay.pojo.Response.FriendShipResponse;
import com.chat.wesay.pojo.Result;
import com.chat.wesay.pojo.User;
import com.chat.wesay.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
* @author abcde
* @description 针对表【friendship】的数据库操作Service实现
* @createDate 2024-08-06 00:41:15
*/
@Service
public class FriendshipServiceImpl extends ServiceImpl<FriendshipMapper, Friendship>
    implements FriendshipService {
   @Autowired
   FriendshipMapper friendshipMapper;
    public List<FriendShipResponse> getFriendByUserId(Long userId){
        try{
            List<FriendShipResponse> friendShipResponses = friendshipMapper.selectFriendInfo(userId);
            return friendShipResponses;
        }catch (Exception e){
            throw new SystemException();
        }
    }

    public Boolean requestAddFriend(Friendship friendship){
        QueryWrapper<Friendship> queryWrapper=new QueryWrapper<>();
        queryWrapper.allEq(Map.of("user_id",friendship.getUserId(),"friend_id",friendship.getFriendId()));
        Friendship fs = getOne(queryWrapper);
        if(fs!=null){
            throw new BusinessException(Code.SAVE_ERR,"该朋友已添加");
        }
        friendship.setFriendshipStatus(0);
        return save(friendship);
    }

    public Boolean agreeAddFriend(Friendship friendship){
        LambdaUpdateWrapper<Friendship> lu=new LambdaUpdateWrapper<>();
        lu.set(Friendship::getFriendshipStatus,1).eq(Friendship::getFriendshipId,friendship.getFriendshipId());
        boolean b = update(new Friendship(),lu);
        if(b){
            Friendship fs=new Friendship();
            fs.setUserId(friendship.getUserId());
            fs.setFriendId(friendship.getFriendId());
            fs.setFriendshipStatus(1);
            return save(fs);
        }
        return b;
    }

    public List<FriendShipResponse> getRequestFriendList(Long userId){
        List<FriendShipResponse> friendShipResponses = friendshipMapper.selectRequestFriend(userId);
        return friendShipResponses;
    }

    public Boolean deleteFriend(Long userId, Long friendId) {
        LambdaQueryWrapper<Friendship> lq=new LambdaQueryWrapper<>();
        lq.eq(Friendship::getUserId,userId)
                .eq(Friendship::getFriendId,friendId)
                .or()
                .eq(Friendship::getFriendId,userId)
                .eq(Friendship::getUserId,friendId);
        return remove(lq);
    }
}




