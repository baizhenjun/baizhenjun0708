package com.example.java_springboot.annotation;

import com.example.java_springboot.constant.AuditCodeEnum;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AuditAnnotation {
    AuditCodeEnum auditType();
    String value() default "";
}
