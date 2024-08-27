package com.chat.wesay.mapper;

import com.chat.wesay.pojo.UnreadMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author abcde
* @description 针对表【unread_message】的数据库操作Mapper
* @createDate 2024-08-17 20:17:20
* @Entity com.chat.wesay.pojo.UnreadMessage
*/
@Mapper
public interface UnreadMessageMapper extends BaseMapper<UnreadMessage> {

}




