package com.example.java_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResponseMessage<T> {

    private Integer code;
    private String message;
    private T body;

    public static <T>ResponseMessage<T> ok(T body){
        return new ResponseMessage().setCode(200).setMessage("").setBody(body);
    }

    public static <T>ResponseMessage<T> ok(T body, String message){
        return new ResponseMessage().setCode(200).setMessage(message).setBody(body);
    }

    public static <T>ResponseMessage<T> error(T body){
        return new ResponseMessage().setCode(500).setMessage("服务器异常！").setBody(body);
    }

    public static <T>ResponseMessage<T> error(T body, String message){
        return new ResponseMessage().setCode(500).setMessage(message).setBody(body);
    }
}
