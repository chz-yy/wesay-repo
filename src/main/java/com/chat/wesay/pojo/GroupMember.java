package com.chat.wesay.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName group_member
 */
@TableName(value ="group_member")
@Data
public class GroupMember implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 1:管理员 0：普通成员
     */
    @TableField(value = "is_admin")
    private Integer isAdmin;

    /**
     * 加入时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}