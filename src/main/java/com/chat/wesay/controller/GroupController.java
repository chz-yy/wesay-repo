package com.chat.wesay.controller;

import com.chat.wesay.constant.Code;
import com.chat.wesay.pojo.Group;
import com.chat.wesay.pojo.Request.GroupMemberRequest;
import com.chat.wesay.pojo.Result;
import com.chat.wesay.service.impl.GroupMemberServiceImpl;
import com.chat.wesay.service.impl.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    GroupServiceImpl groupService;
    @PostMapping("createGroup")
    public Result createGroup(@RequestBody GroupMemberRequest members){
        Boolean b = groupService.createGroup(members);
        return b?Result.Success(Code.SAVE_OK,b):Result.Error(Code.SAVE_ERR,"创建失败");
    }

    @GetMapping("list")
    public  Result getGroupList(@RequestParam Long userId){
        List<Group> groupList = groupService.getGroupList(userId);
        return Result.Success(Code.GET_OK,groupList);
    }


}
