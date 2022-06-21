package com.tong.quartz.exception;

import lombok.Getter;

public enum ErrorHandler {

    INTERNAL_SERVER_ERROR(50001, "服务器内部错误");

    @Getter
    private String templateMessage;

    @Getter
    private final Integer code;
    private final String msg;

    ErrorHandler(Integer code, String templateMessage) {
        this.code = code;
        this.msg = templateMessage;
        this.templateMessage = String.format(templateMessage, "");
    }

    public BusinessException build(Throwable cause) {
        return new BusinessException(this, cause);
    }

    public BusinessException build(Throwable cause, String templateMsgPara) {
        this.templateMessage = String.format(msg, templateMsgPara);
        return new BusinessException(this, cause);
    }
}
