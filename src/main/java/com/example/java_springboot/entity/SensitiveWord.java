package com.example.java_springboot.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName: SensitiveWord
 * @Description:
 * @author: baizhenjun
 * @date: 2023/3/3 14:20
 */
@Data
@Builder
public class SensitiveWord{
    private Integer id;

    private String content;
}
