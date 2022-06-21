package com.tong.quartz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerHandlerException {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result ControllerException(BusinessException ex) {
        return HttpResponse.error(ex.getErrorHandler().getCode(),
                ex.getErrorHandler().getTemplateMessage(),
                ex.getStackTrace());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result ControllerException(Exception ex) {
        return HttpResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                ex.getStackTrace());
    }
}

