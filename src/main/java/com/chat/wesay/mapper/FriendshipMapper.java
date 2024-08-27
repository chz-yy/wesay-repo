package com.chat.wesay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chat.wesay.pojo.Friendship;
import com.chat.wesay.pojo.Response.FriendShipResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author abcde
* @description 针对表【friendship】的数据库操作Mapper
* @createDate 2024-08-06 00:41:15
* @Entity .pojo.com.chat.wesay.pojo.Friendship
*/
@Mapper
public interface FriendshipMapper extends BaseMapper<Friendship> {
    List<FriendShipResponse> selectRequestFriend(Long userId);

    List<FriendShipResponse> selectFriendInfo(Long userId);

}




