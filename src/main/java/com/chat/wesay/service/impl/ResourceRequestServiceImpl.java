package com.chat.wesay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chat.wesay.pojo.Request.ResourceRequest;
import com.chat.wesay.service.ResourceRequestService;
import com.chat.wesay.mapper.ResourceRequestMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author abcde
* @description 针对表【resource_request】的数据库操作Service实现
* @createDate 2024-08-19 18:08:30
*/
@Service
public class ResourceRequestServiceImpl extends ServiceImpl<ResourceRequestMapper, ResourceRequest>
    implements ResourceRequestService{

    public List<ResourceRequest> getRequestList(Long userId) {
        LambdaQueryWrapper<ResourceRequest> lq=new LambdaQueryWrapper<>();
        lq.eq(ResourceRequest::getReceiverId,userId)
                .orderByDesc(ResourceRequest::getCreateTime);
        List<ResourceRequest> list = list(lq);
        return list;
    }

    public List<ResourceRequest> getMyRequestList(Long userId) {
        LambdaQueryWrapper<ResourceRequest> lq=new LambdaQueryWrapper<>();
        lq.eq(ResourceRequest::getSenderId,userId)
                .orderByDesc(ResourceRequest::getCreateTime);
        List<ResourceRequest> list = list(lq);
        return list;
    }
}




