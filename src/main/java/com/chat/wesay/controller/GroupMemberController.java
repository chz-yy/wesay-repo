package com.chat.wesay.controller;

import com.chat.wesay.constant.Code;
import com.chat.wesay.pojo.Response.GroupMemberInfoResponse;
import com.chat.wesay.pojo.Result;
import com.chat.wesay.service.impl.GroupMemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groupMember")
public class GroupMemberController {

    @Autowired
    GroupMemberServiceImpl groupMemberService;

    @GetMapping("memberInfo")
    public Result groupMemberInfo(Long groupId){
        List<GroupMemberInfoResponse> memberInfo = groupMemberService.getMemberInfoByGroupId(groupId);
        return Result.Success(Code.GET_OK,memberInfo);
    }

    @DeleteMapping("exitGroup")
    public Result exitGroup(@RequestParam Long userId, @RequestParam Long groupId){
        Boolean b = groupMemberService.exitGroup(userId, groupId);
        return b?Result.Success(Code.DELETE_OK,""):Result.Error(Code.DELETE_ERR,"退出组失败");
    }
}
