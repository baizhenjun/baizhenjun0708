package com.example.java_springboot.batis;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 所有BEAN文件必须继承，用于实现基础功能
 *@author Bryan Wang
 */
public class BaseBean implements Serializable {
    private static final long serialVersionUID = -7989386461059579170L;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
