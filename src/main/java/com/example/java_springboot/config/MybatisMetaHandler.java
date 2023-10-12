package com.example.java_springboot.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class MybatisMetaHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.setFieldValByName("createTime", localDateTime, metaObject);// 创建时间
        this.setFieldValByName("updateTime", localDateTime, metaObject);// 创建时间
        this.setFieldValByName("createUserName", "白镇军", metaObject);// 创建人uuid
        this.setFieldValByName("createUserUuid", "白镇军", metaObject);// 创建人uuid
        this.setFieldValByName("updateUserName", "白镇军", metaObject);// 创建人uuid
        this.setFieldValByName("updateUserUuid", "白镇军", metaObject);// 创建人uuid

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.setFieldValByName("updateTime", localDateTime, metaObject);// 修改时间
        this.setFieldValByName("updateUserName", "白镇军", metaObject);// 修改人uuid
        this.setFieldValByName("updateUserUuid","白镇军", metaObject);// 修改人uuid
    }
}
