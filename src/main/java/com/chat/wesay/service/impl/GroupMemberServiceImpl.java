package com.chat.wesay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chat.wesay.mapper.GroupMapper;
import com.chat.wesay.pojo.GroupMember;
import com.chat.wesay.pojo.Response.GroupMemberInfoResponse;
import com.chat.wesay.service.GroupMemberService;
import com.chat.wesay.mapper.GroupMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author abcde
* @description 针对表【group_member】的数据库操作Service实现
* @createDate 2024-08-06 00:50:32
*/
@Service
public class GroupMemberServiceImpl extends ServiceImpl<GroupMemberMapper, GroupMember>
    implements GroupMemberService{
    @Autowired
    GroupMemberMapper groupMemberMapper;
    @Autowired
    GroupServiceImpl groupService;
    public List<GroupMember> getMembersByGroupId(Long groupId){
        LambdaQueryWrapper<GroupMember> lq=new LambdaQueryWrapper<>();
        lq.eq(GroupMember::getGroupId,groupId);
        List<GroupMember> list = list(lq);
        return list;
    }

    public List<GroupMemberInfoResponse> getMemberInfoByGroupId(Long groupId){
        List<GroupMemberInfoResponse> info = groupMemberMapper.getMemberInfoByGroupId(groupId);
        return info;
    }

    public Boolean exitGroup(Long userId, Long groupId) {
        LambdaQueryWrapper<GroupMember> lq=new LambdaQueryWrapper<>();
        lq.eq(GroupMember::getGroupId,groupId)
                .eq(GroupMember::getUserId,userId);
        boolean remove = remove(lq);
        if(getMembersByGroupId(groupId).isEmpty()){
            groupService.removeById(groupId);
        }
        return remove;
    }
}




