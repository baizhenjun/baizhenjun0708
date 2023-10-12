package com.example.java_springboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname ExcelValid
 * @Description TODO
 * @Date 2022/7/22 17:02
 * @Created by HY
 */

@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelValid {

    String message() default "导入有未填入的字段";
}
