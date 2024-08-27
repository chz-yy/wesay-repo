package com.chat.wesay.pojo.Request;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @TableName resource_request
 */
@TableName(value ="resource_request")
@Data
public class ResourceRequest implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

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
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "student_id")
    private Integer studentId;

    /**
     * 
     */
    @TableField(value = "runtime")
    private String runtime;

    /**
     * 
     */
    @TableField(value = "resource")
    private String resource;

    @TableField(value = "is_agree")
    private Integer isAgree;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}