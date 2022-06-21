package com.tong.quartz.exception;

import org.springframework.http.HttpStatus;

public class HttpResponse {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(HttpStatus.OK.value());
        result.setMessage(HttpStatus.OK.toString());
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(HttpStatus.OK);
    }

    public static Result error(Integer code, String msg, Object ob) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(ob);
        return result;
    }
}
