package com.example.java_springboot.util;


import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @Classname StringCommonUtil
 * @Description 字符串工具类
 * @Date 2023/1/10 11:35
 * @Created by baizhenjun
 */
public class StringCommonUtil {


    private static final String splitChar = ",";

    public static List<Integer> strToIntList(String str) {
        return strToIntList(str, splitChar);
    }

    /**
     * 字符串转List<Integer>
     * @param str
     * @param splitChar
     * @return
     */
    public static List<Integer> strToIntList(String str,String splitChar) {
        List<Integer> result = Lists.newArrayList();
        String[] split = StringUtils.split(str, splitChar);
        List<String> list = Arrays.asList(split);
        list.forEach(s->result.add(Integer.valueOf(s)));
        return result;
    }

    public static List<String> strToStrList(String str) {
        return strToStrList(str, splitChar);
    }

    /**
     * 按指定字符 切割字符串并返回数组
     * @param str
     * @param splitChar
     * @return
     */
    public static List<String> strToStrList(String str,String splitChar) {
        List<String> result = Lists.newArrayList();
        String[] split = StringUtils.split(str, splitChar);
        List<String> list = Arrays.asList(split);
        result.addAll(list);
        return result;
    }

    /**
     * 验证所有传入的参数
     * 1、非空
     * 2、非""
     * 返回true，即验证通过
     *
     * @param params
     * @return
     */
    public static boolean verifyParams(String... params) {
        int count = 0;
        for (int i = 0; i < params.length; i++) {
            //遍历字符数组所有的参数，发现某个为 null 或者 "" ,则跳出
            if (StringUtils.isEmpty(params[i])) {
                return false;
            }
            count++;
        }
        if (count == params.length) {
            return true;
        }
        return false;
    }

    /**
     * 按指定数字 切割字符串并返回数组
     * @param str
     * @param num
     * @return
     */
    public static List<String> splitStringByNum(String str,int num){
        List<String> result = Lists.newArrayList();
        if (StringUtils.isNotEmpty(str)){
            for (int i = 0; i < str.length(); i+=num) {
                result.add(str.substring(0,i+num));
            }
        }
        return result;
    }
}
