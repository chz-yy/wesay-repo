package com.chat.wesay.controller;

import com.chat.wesay.constant.Code;
import com.chat.wesay.pojo.Friendship;
import com.chat.wesay.pojo.Response.FriendShipResponse;
import com.chat.wesay.pojo.Result;
import com.chat.wesay.pojo.User;
import com.chat.wesay.service.impl.FriendshipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friendship")
public class FriendshipController {

    @Autowired
    FriendshipServiceImpl friendshipService;
    @GetMapping("list")
    public Result getFriendList(@RequestParam Long userId){
        List<FriendShipResponse> list = friendshipService.getFriendByUserId(userId);
        return Result.Success(Code.GET_OK,list);
    }

    @PostMapping("requestAddFriend")
    public Result requestAddFriend(@RequestBody Friendship friendship){
        boolean save = friendshipService.requestAddFriend(friendship);
        return save?Result.Success(Code.SAVE_OK,""):Result.Error(Code.SAVE_ERR,"请求失败");
    }

    @PostMapping("agreeAddFriend")
    public Result agreeAddFriend(@RequestBody Friendship friendship){
        Boolean b = friendshipService.agreeAddFriend(friendship);
        return b?Result.Success(Code.SAVE_OK,""):Result.Error(Code.SAVE_ERR,"同意好友请求失败");
    }

    @GetMapping("getRequestFriendList")
    public Result getRequestFriendList(@RequestParam Long userId){
        return Result.Success(Code.GET_OK,friendshipService.getRequestFriendList(userId));
    }

    @DeleteMapping("deleteFriend")
    public Result deleteFriend(@RequestParam Long userId,@RequestParam Long friendId){
        Boolean b = friendshipService.deleteFriend(userId, friendId);
        return b?Result.Success(Code.DELETE_OK,""):Result.Error(Code.DELETE_ERR,"删除好友失败");
    }
}
