package com.chat.wesay.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;
    public static Result Success(Integer code,Object data){
        return new Result(code,"",data);
    }

    public static Result Error(Integer code,String msg){
        return new Result(code,msg,null);
    }
}
