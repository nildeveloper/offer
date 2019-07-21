package com.lhl.boot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.edu.bean.Msg;

import java.util.List;
import java.util.Map;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2018-11-08
 * Time: 14:38
 * Description: FastJsonUtils 工具类
 */
public class FastJsonUtils {
    
    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }
    
    private FastJsonUtils() {
        System.out.println("禁止初始化！");
    }

    private static final SerializerFeature[] features = {
            SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };


    public static String objectToJSON(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }
    
    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    // 转换为数组
    public static <T> Object[] toArray(String text) {
        return toArray(text, null);
    }

    // 转换为数组
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    // 转换为List
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }
    
    // 转换为Map
    public static Map<String, Object> toMap(String jsonStr) {
        try {
            return JSON.parseObject(jsonStr, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将string转化为序列化的json字符串
     * @param text
     * @return
     */
    public static Object textToJson(String text) {
        Object objectJson  = JSON.parse(text);
        return objectJson;
    }

    /**
     * json字符串转化为map
     * @param s
     * @return
     */
    public static <K, V> Map<K, V> stringToMap(String s) {
        Map<K, V> m = (Map<K, V>) JSONObject.parseObject(s);
        return m;
    }

    /**
     * 转换JSON字符串为对象
     * @param jsonData
     * @param clazz
     * @return
     */
    public static Object jsonToObject(String jsonData, Class<?> clazz) {
        return JSONObject.parseObject(jsonData, clazz);
    }

    /**
     * 将map转化为string
     * @param m
     * @return
     */
    public static <K, V> String mapToString(Map<K, V> m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }

    /**
     * json 转化为 Msg
     * @param res
     * @param clazz
     * @return
     */
    public static Msg toMsg(String res, Class<?> clazz) {
        Msg msg = toBean(res, Msg.class);
        Map<String, Object> map = msg.getExtend();
        // msg 中extend 中的 list 是一个JSONArray, Object 是一个 JSONObject
        if (map.size() != 0) {
            for (String k : map.keySet()) {
                if (map.get(k) instanceof JSONArray) {
                    JSONArray jsonArray = (JSONArray) map.get(k);
                    List list =  jsonArray.toJavaList(clazz);
                    map.remove(k);  // 删除map中原来的key对应的实体
                    map.put(k, list);
                } else if (map.get(k) instanceof JSONObject) {
                    JSONObject jsonObject = (JSONObject) map.get(k);
                    Object object = jsonObject.toJavaObject(clazz);
                    map.remove(k);
                    map.put(k, object);
                }
            }
        }
        return msg;
    }
    
}
