package com.chat.wesay.exception;

public class SystemException extends RuntimeException{
    public SystemException(Integer code,String message ) {
        super(message);
        this.code = code;
    }

    public SystemException(Integer code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
    public SystemException(){

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    private Integer code;


}
