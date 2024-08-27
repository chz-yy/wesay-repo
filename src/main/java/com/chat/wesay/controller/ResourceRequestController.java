package com.chat.wesay.controller;

import com.chat.wesay.constant.Code;
import com.chat.wesay.pojo.Request.ResourceRequest;
import com.chat.wesay.pojo.Result;
import com.chat.wesay.service.impl.ResourceRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resource")
public class ResourceRequestController {
    @Autowired
    ResourceRequestServiceImpl resourceRequestService;

    @GetMapping("list")
    public Result getRequestList(@RequestParam Long userId){
        List<ResourceRequest> requestList = resourceRequestService.getRequestList(userId);
        return Result.Success(Code.GET_OK,requestList);
    }

    @GetMapping("myList")
    public Result getMyRequestList(@RequestParam Long userId){
        List<ResourceRequest> requestList = resourceRequestService.getMyRequestList(userId);
        return Result.Success(Code.GET_OK,requestList);
    }

    @PostMapping("request")
    public Result requestResource(@RequestBody ResourceRequest rq){
        rq.setIsAgree(0);
        boolean save = resourceRequestService.save(rq);
        return save?Result.Success(Code.SAVE_OK,rq.getId()):Result.Error(Code.SAVE_ERR,"提交请求失败");
    }

    @PutMapping("agree")
    public Result agreeRequest(@RequestBody ResourceRequest rq){
        rq.setIsAgree(1);
        boolean b = resourceRequestService.updateById(rq);
        return b?Result.Success(Code.UPDATE_OK,b):Result.Error(Code.UPDATE_ERR,"同意请求失败");
    }


}
