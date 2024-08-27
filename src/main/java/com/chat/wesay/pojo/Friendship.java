package com.chat.wesay.pojo;


import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName friendship
 */
@TableName(value ="friendship")
@Data
public class Friendship implements Serializable {
    /**
     * 
     */
    @TableId(value = "friendship_id")
    private Long friendshipId;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 
     */
    @TableField(value = "friend_id")
    private Long friendId;

    /**
     * 1:已确认 0：未确认
     */
    @TableField(value = "friendship_status")
    private Integer friendshipStatus;

    /**
     * 
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}