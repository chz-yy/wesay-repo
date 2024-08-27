package com.chat.wesay.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(Integer code,String message ) {
        super(message);
        this.code = code;
    }

    public BusinessException(Integer code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    private Integer code;
}
