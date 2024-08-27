package com.chat.wesay.mapper;

import com.chat.wesay.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author abcde
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-08-06 11:24:35
* @Entity generater.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




