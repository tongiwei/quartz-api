package com.tong.quartz.exception;

import lombok.Getter;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 52174510148L;

    @Getter
    private ErrorHandler errorHandler;

    @Getter
    private Object additional;

    public BusinessException(ErrorHandler errorHandler) {
        super(errorHandler.getTemplateMessage());
        this.errorHandler = errorHandler;
    }

    public BusinessException(ErrorHandler errorHandler, Throwable cause) {
        super(cause);
        this.errorHandler = errorHandler;
    }

    public BusinessException additional(Object additional) {
        this.additional = additional;
        this.errorHandler = ErrorHandler.INTERNAL_SERVER_ERROR;
        return this;
    }
}
