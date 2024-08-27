package com.chat.wesay.mapper;

import com.chat.wesay.pojo.GroupMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chat.wesay.pojo.Response.GroupMemberInfoResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author abcde
* @description 针对表【group_member】的数据库操作Mapper
* @createDate 2024-08-06 00:50:32
* @Entity com.chat.wesay.pojo.GroupMember
*/
@Mapper
public interface GroupMemberMapper extends BaseMapper<GroupMember> {
    List<GroupMemberInfoResponse> getMemberInfoByGroupId(Long groupId);
}




