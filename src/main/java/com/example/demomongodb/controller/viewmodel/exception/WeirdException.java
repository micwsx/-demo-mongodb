package com.example.demomongodb.controller.viewmodel.exception;

import org.bson.types.Code;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: Michael
 * @date: 4/12/2022 9:49 PM
 * customized exception class
 * 自定义异常响应码和消息
 */
@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason = "something comes up!")
public class WeirdException extends  RuntimeException{

    public WeirdException() {
    }

    public WeirdException(String message) {
        super(message);
    }

    public WeirdException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeirdException(Throwable cause) {
        super(cause);
    }

    public WeirdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
