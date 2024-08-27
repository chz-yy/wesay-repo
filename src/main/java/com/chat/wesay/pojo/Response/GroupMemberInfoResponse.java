package com.chat.wesay.pojo.Response;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class GroupMemberInfoResponse {
    private Long groupId;
    private Long userId;
    private String username;
    private String avatar;
    private Integer isAdmin;
}
