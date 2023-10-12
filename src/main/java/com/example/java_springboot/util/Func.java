package com.example.java_springboot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import org.assertj.core.util.Lists;

@Api(value = "公共方法类")
public class Func {
    public static <T>T mapToBean(Map map, Class<T> t){
        return JSON.parseObject(JSON.toJSONString(map), t);
    }

    public static Map beanToMap(Object bean){
        return JSON.parseObject(JSON.toJSONString(bean), Map.class);
    }

    public static String jsonObjToString(Object o){
        return JSON.toJSONString(o);
    }

    public static Map beanToMapSnakeCase(Object object) {
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        String json = JSON.toJSONString(object, config);
        return JSON.parseObject(json, Map.class);
    }

    public static Boolean isNull(Object o){
        return null == o;
    }

    public static Boolean notNull(Object o){
        return null != o;
    }

    public static Boolean isEmpty(Object o){
        if (o == null) {
            return true;
        } else if(o instanceof String){
            return "".equals(o);
        } else if (o instanceof List) {
            return CollectionUtils.isEmpty((List)o);
        } else if (o instanceof Page) {
            Object records = ((Page) o).getRecords();
            return CollectionUtils.isEmpty((List)records);
        } else {
            return false;
        }
    }

    public static Boolean notEmpty(Object o) {
        return !isEmpty(o);
    }

    public static String formatDate(Date date, String pattern){
        return DateUtil.formatDate(date, pattern);
    }

    public static Object copyProperties(Object sourceBean, Object targetBean){
        BeanUtils.copyProperties(sourceBean, targetBean);
        return targetBean;
    }

    /**
     * 只能转换同属性名，且相同类型的字段，基础类型与其对象类型也可
     * @param sourceBean
     * @param t
     * @param <T>
     * @return
     */
    public static <T>T copyProperties(Object sourceBean, Class<T> t){
        T targetBean = null;
        try {
            targetBean = t.newInstance();
            BeanUtils.copyProperties(sourceBean, targetBean);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return targetBean;
    }

    /**
     * 会转换同属性名，但不同类型的字段
     * @param sourceBean
     * @param t
     * @param <T>
     * @return
     */
    public static <T>T formatSource(Object sourceBean, Class<T> t){
        String jsonStr = JSON.toJSONString(sourceBean);
        return JSON.parseObject(jsonStr, t);
    }

    public static <T>List<T> formatSource(List sourceBean, Class<T> t){
        List<T> result = new ArrayList<>();
        for(Object source: sourceBean){
            result.add(formatSource(source, t));
        }
        return result;
    }

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 集合内对象复制并返回
     * 集合中套集合时里面的集合复制不了，需要clone
     * @param sourceList
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> copyBeanList(List<?> sourceList, Class<T> t) {
        List<T> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(sourceList)) {
            return Lists.newArrayList();
        }
        try {
            for (Object s : sourceList) {
                T targetBean = t.newInstance();
                BeanUtils.copyProperties(s, targetBean);
                result.add(targetBean);
            }
        } catch (Exception e) {
        }

        return result;
    }

    /**
     * 获取百分比
     * @param dividend
     * @param divisor
     * @return
     */
    public static String getRate(BigDecimal dividend, BigDecimal divisor){
        double val = dividend.divide(divisor, 3, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)).doubleValue();
        return val == 0. ? "0" : (val + "%");
    }

    /**
     * 数字转字符串，指定位数，前补0
     * @return
     */
    public static String numberFormatStr(Integer number, Integer digit){
        StringBuilder f = new StringBuilder("0");
        for(int i=1; i<digit; i++){
            f.append("0");
        }
        DecimalFormat format = new DecimalFormat(f.toString());
        return format.format(number);
    }

    /**
     * 属性去重
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
    }

    public static String convert(int number) {
        // 汉字
        String[] num = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};
        // 单位
        String[] unit = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千", "万亿"};
        String result = String.valueOf(number);
        char[] ch = result.toCharArray();
        String str = "";
        int length = ch.length;
        for (int i = 0; i < length; i++) {
            int c = (int) ch[i] - 48;
            if (c != 0) {
                str += num[c - 1] + unit[length - i - 1];
            }
        }
        return str;
    }
}
