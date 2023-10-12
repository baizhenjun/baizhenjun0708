package com.example.java_springboot.batis;


import lombok.Data;

import java.util.HashMap;

/**
 * 返回对象工具类
 *@author Bryan Wang
 */
@Data
public class BaseResponse<T> extends BaseBean {
    private int code;
    private String msg;
    private T result;

    protected BaseResponse(int code, T data) {
        this.code = code;
        this.result = data;
    }
    protected BaseResponse(int code, String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.result = data;
    }
    protected BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> BaseResponse<HashMap<String, Object>> success(T data)
    {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("records",data);
        return new BaseResponse<HashMap<String, Object>>(ResultEnum.SUCCESS.getCode(),
                ResultEnum.SUCCESS.getMessage(),objectObjectHashMap);
    }
    public static <T> BaseResponse<T> success()
    {
        return new BaseResponse<>(ResultEnum.SUCCESS.getCode()
                ,ResultEnum.SUCCESS.getMessage());
    }
    public static <T> BaseResponse<T> error(ResultEnum resultEnum)
    {
        return new BaseResponse<>(resultEnum.getCode(), resultEnum.getMessage());
    }
    public static <T> BaseResponse<T> error(int code, String msg)
    {
        return new BaseResponse<>(code, msg);
    }
}
