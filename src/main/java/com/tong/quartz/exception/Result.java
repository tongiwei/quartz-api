package com.tong.quartz.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Result implements Serializable {

    private static final long serialVersionUID = 7595774502616748829L;

    private Integer code;
    private String message;
    private Object data;

}