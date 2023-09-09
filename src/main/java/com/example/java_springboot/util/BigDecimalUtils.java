package com.example.java_springboot.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @description:
 * @projectName: auditService
 * @see: com.audit.base.util
 * @author: baizhenjun
 * @date: 2023/5/23 11:20 上午
 */
public class BigDecimalUtils {

    /**
     * BigDecimal的除法运算封装，如果除数或者被除数为0，返回默认值
     * 默认返回小数位后4位，用于金额计算
     *
     * @param b1
     * @param b2
     * @param defaultValue
     * @return
     */
    public static <T extends Number> BigDecimal safeDivide(T b1, T b2, BigDecimal defaultValue) {
        return safeDivide(b1,b2,defaultValue,4);
    }


    /**
     * BigDecimal的除法运算封装，如果除数或者被除数为0，返回默认值
     * 默认返回小数位后4位，用于金额计算
     *
     * @param b1
     * @param b2
     * @param defaultValue
     * @param scale
     * @return
     */
    public static <T extends Number> BigDecimal safeDivide(T b1, T b2, BigDecimal defaultValue,int scale) {
        if (null == b1 || null == b2) {
            return defaultValue;
        }
        if(b2.intValue()==0){
            return defaultValue;
        }
        try {
            return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), scale, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 格式化百分数
     * @param value
     * @return
     */
    public static String formatPercentage(BigDecimal value) {
        if(null == value){
            return "";
        }
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMaximumFractionDigits(2);
        return format.format(value.setScale(4,BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 保留两位小数
     * @return
     */
    public static String format(BigDecimal decimal){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(decimal);
    }
}
