package com.example.java_springboot.util;

/**
 * @ClassName: CloneUtils
 * @Description:
 * @author: baizhenjun
 * @date: 2023/3/28 11:37
 */
import java.io.*;
import java.util.*;

public class CloneUtils {
    /**
     * 对象深克隆方法
     * @param obj 需要克隆的对象
     * @return 克隆后的新对象
     */
    public static <T extends Serializable> T clone(T obj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (T) ois.readObject();
    }
    /**
     * 对List集合进行深克隆
     * @param list 需要克隆的List集合
     * @return 克隆后的新List集合
     */
    public static <T extends Serializable> List<T> cloneList(List<T> list) throws IOException, ClassNotFoundException {
        List<T> cloneList = new ArrayList<>(list.size());
        for (T obj : list) {
            cloneList.add(clone(obj));
        }
        return cloneList;
    }
    /**
     * 对Set集合进行深克隆
     * @param set 需要克隆的Set集合
     * @return 克隆后的新Set集合
     */
    public static <T extends Serializable> Set<T> cloneSet(Set<T> set) throws IOException, ClassNotFoundException {
        Set<T> cloneSet = new HashSet<>(set.size());
        for (T obj : set) {
            cloneSet.add(clone(obj));
        }
        return cloneSet;
    }
    /**
     * 对Map集合进行深克隆
     * @param map 需要克隆的Map集合
     * @return 克隆后的新Map集合
     */
    public static <K extends Serializable, V extends Serializable> Map<K, V> cloneMap(Map<K, V> map) throws IOException, ClassNotFoundException {
        Map<K, V> cloneMap = new HashMap<>(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            cloneMap.put(clone(key), clone(value));
        }
        return cloneMap;
    }
}
