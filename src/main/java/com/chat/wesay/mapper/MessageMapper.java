package com.chat.wesay.mapper;

import com.chat.wesay.pojo.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author abcde
* @description 针对表【message】的数据库操作Mapper
* @createDate 2024-08-06 00:32:03
* @Entity com.chat.wesay.pojo.Message
*/
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}




