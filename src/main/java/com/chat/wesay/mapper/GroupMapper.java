package com.chat.wesay.mapper;

import com.chat.wesay.pojo.Group;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author abcde
* @description 针对表【group】的数据库操作Mapper
* @createDate 2024-08-06 00:49:41
* @Entity com.chat.wesay.pojo.Group
*/
@Mapper
public interface GroupMapper extends BaseMapper<Group> {
    List<Group> getGroupListByUserId(Long userId);
}




