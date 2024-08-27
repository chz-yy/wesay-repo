package com.chat.wesay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chat.wesay.mapper.GroupMemberMapper;
import com.chat.wesay.pojo.Group;
import com.chat.wesay.pojo.GroupMember;
import com.chat.wesay.pojo.Request.GroupMemberRequest;
import com.chat.wesay.pojo.Response.FriendShipResponse;
import com.chat.wesay.pojo.Response.GroupMemberInfoResponse;
import com.chat.wesay.service.GroupService;
import com.chat.wesay.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author abcde
* @description 针对表【group】的数据库操作Service实现
* @createDate 2024-08-06 00:49:41
*/
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group>
    implements GroupService{
    @Autowired
    @Lazy
    GroupMemberServiceImpl groupMemberService;
    @Autowired
    GroupMapper groupMapper;
    public Boolean createGroup(GroupMemberRequest members){
        if(members.getFriendId().size()==1){
            return false;
        }
        Group g=new Group();
        g.setGroupName(members.getGroupName());
        g.setCreatedBy(members.getUserId());
        g.setGroupAvatar(members.getGroupAvatar());
        boolean s = this.save(g);
        if(s){
            Long groupId=g.getGroupId();
            List<GroupMember> groupMembers=new ArrayList<>();
            GroupMember admin=new GroupMember();
            admin.setGroupId(groupId);
            admin.setUserId(members.getUserId());
            admin.setIsAdmin(1);
            groupMembers.add(admin);
            for(Long userId:members.getFriendId()){
                GroupMember member=new GroupMember();
                member.setIsAdmin(0);
                member.setGroupId(groupId);
                member.setUserId(userId);
                groupMembers.add(member);
            }
            boolean b = groupMemberService.saveBatch(groupMembers);
            return b;
        }
        return s;
    }


    public List<Group> getGroupList(Long userId) {
        List<Group> groupList = groupMapper.getGroupListByUserId(userId);
        return groupList;
    }



}




