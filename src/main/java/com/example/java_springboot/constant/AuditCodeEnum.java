package com.example.java_springboot.constant;

public enum AuditCodeEnum {

    TYPE_SUBMIT("0", "提交"),
    TYPE_AUDIT("1", "审批");

    String value;
    String text;
    private AuditCodeEnum(String value, String text){
        this.value = value;
        this.text = text;
    }

    public String getValue(){
        return this.value;
    }
}

