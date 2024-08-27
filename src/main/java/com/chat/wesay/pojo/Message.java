package com.chat.wesay.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName message
 */
@TableName(value ="message")
@Data
public class Message implements Serializable {
    /**
     * 
     */
    @TableId(value = "message_id")
    private Long messageId;

    /**
     * 
     */
    @TableField(value = "sender_id")
    private Long senderId;

    /**
     * 
     */
    @TableField(value = "receiver_id")
    private Long receiverId;

    /**
     * 
     */
    @TableField(value = "content")
    private String content;

    /**
     * 1:已读 0：未读
     */
    @TableField(value = "is_read")
    private Integer isRead;

    /**
     * 
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 1:个人 0：群聊
     */
    @TableField(value = "chat_type")
    private Integer chatType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}