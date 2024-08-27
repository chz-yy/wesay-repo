package com.chat.wesay.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user_group
 */
@TableName(value ="user_group")
@Data
public class Group implements Serializable {
    /**
     * 
     */
    @TableId(value = "group_id")
    private Long groupId;

    /**
     * 
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 创建群聊的用户id
     */
    @TableField(value = "created_by")
    private Long createdBy;

    /**
     * 
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "group_avatar")
    private String groupAvatar;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}