package com.example.java_springboot.constant;

import com.google.common.collect.Maps;

import java.util.Map;

public class StaticCode {

    // "是" or "否" 代码集
    public static final Map<String, Object> EvaluateStatus_Code = Maps.newHashMap();

    static {
        EvaluateStatus_Code.put("0", "未开始");
        EvaluateStatus_Code.put("1", "开始");
        EvaluateStatus_Code.put("2", "结束");
    }
}
