package com.example.java_springboot.batis;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class IceException extends RuntimeException {

    private Integer code = 500;
    private String message;

    public IceException(String message){
        this.message = message;
    }

    public IceException(String message, Integer code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
