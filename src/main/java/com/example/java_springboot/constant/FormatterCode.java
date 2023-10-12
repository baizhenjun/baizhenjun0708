package com.example.java_springboot.constant;

public interface FormatterCode {
    Object getInfo();

    enum FormatterCodeEnum implements FormatterCode {

        // "是" or "否" 代码集
        EvaluateStatus_Code {
            public Object getInfo() {
                return StaticCode.EvaluateStatus_Code;
            }
        }
    }
}
