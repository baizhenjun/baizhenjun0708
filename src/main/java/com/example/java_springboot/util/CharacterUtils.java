package com.example.java_springboot.util;

import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: CharacterFilter
 * @Description:
 * @author: baizhenjun
 * @date: 2022/1/19 9:50
 */
@Data
public class CharacterUtils {
    private static Pattern pattern= Pattern.compile("[^a-zA-Z0-9_\\u4e00-\\u9fa5]*");

    public static String charFilter(String str){
        // 指定设置非法字符
        // Pattern pattern = Pattern.compile("[@#]");
        Matcher matcher = pattern.matcher(str);
        StringBuffer buffer = new StringBuffer();
        // 如果找到非法字符
        while (matcher.find()) {
            // 如果里面包含非法字符如冒号双引号等，那么就把他们消去，并把非法字符前面的字符放到缓冲区
            matcher.appendReplacement(buffer, "");
        }
        // 将剩余的合法部分添加到缓冲区
        matcher.appendTail(buffer);
        return buffer.toString();
    }
}
