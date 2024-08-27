package com.chat.wesay.exception;

import com.chat.wesay.constant.Code;
import com.chat.wesay.constant.Msg;
import com.chat.wesay.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException e){

        return Result.Error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException e){

        return Result.Error(Code.SYSTEM_ERR, Msg.SYSTEM_ERR);
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception e){
        System.out.println(e);
        return Result.Error(Code.SYSTEM_UNKNOW_ERR, Msg.SYSTEM_UNKNOW_ERR);
    }
}
