package com.chat.wesay.pojo.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupMemberRequest {
    private Long userId;
    private List<Long> friendId;
    private String groupName;
    private String groupAvatar;
}
