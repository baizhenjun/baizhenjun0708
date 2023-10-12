package com.example.java_springboot.annotation;

import com.example.java_springboot.constant.FormatterCode;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CodeField {
    FormatterCode.FormatterCodeEnum code();
    String field();
    boolean isStatic() default true;
}
