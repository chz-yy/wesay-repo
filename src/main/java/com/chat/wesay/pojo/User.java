package com.chat.wesay.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.ASSIGN_ID,value = "user_id")
    private Long userId;
    @TableField(value = "username")
    private String username;
    @TableField(value = "password")
    private String password;
    @TableField(value = "avatar")
    private String avatar;
    @TableField(value = "gender")
    private Integer gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @TableField(value = "birth")
    private Date birth;
    @TableLogic
    @TableField(fill = FieldFill.INSERT,value = "status")
    private Integer status;
    @TableField(fill = FieldFill.INSERT,value = "create_time")
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE,value = "update_time")
    private Date updateTime;
    @TableField(fill = FieldFill.INSERT,value = "last_login_time")
    private Date lastLoginTime;
    @TableField(value = "email")
    private String email;
    @TableField(value = "phone")
    private String phone;
    @TableField(value = "signature")
    private String signature;
    @Version
    private Integer version;


}
