package com.example.java_springboot.batis;


import lombok.Data;

/**
 * @Classname BaseException
 * @Description 公共异常类
 * @Date 2022/1/7 10:20
 * @Created by HY
 */
@Data
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    private Integer code;


    private String msg;


    private ResultEnum resultEnum;


    public BaseException(ResultEnum resultEnum) {
        super();
        this.resultEnum = resultEnum;
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }

    public BaseException(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public BaseException(String message) {
        super(message);
        //复制默认异常
        this.code = 9999;
        this.msg = message;
    }
}
