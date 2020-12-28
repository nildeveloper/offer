package com.lhl.boot.db.helper;

import com.alibaba.fastjson.JSON;
import com.lhl.boot.db.info.ClazzInfo;
import io.vavr.control.Try;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-12-28
 * @time 16:56
 * @describe:
 */
public class DBHelper {

    private static final Map<Class<?>, ClazzInfo> CLAZZ_INFO_CACHE = new ConcurrentHashMap<>();


    /**
     * db result format to class instance
     * @param dbResult
     * @param clazz
     * @param <T>
     * @return
     */
    public static<T> T formatEntity(Map<String, String> dbResult, Class<T> clazz) throws Exception {
        if (dbResult == null || dbResult.isEmpty()) {
            return null;
        }
        ClazzInfo clazzInfo = getClazzInfo(clazz);
        if (clazzInfo == null) {
            return null;
        }
        T instance = clazz.newInstance();
        Map<String, Field> columnFieldInfoMap = clazzInfo.getColumnFieldInfoMap();
        Map<String, Method> writeMethodMap = clazzInfo.getWriteMethodMap();
        // 遍历数据库查询结果，封装instance
        for (Map.Entry<String, String> item : dbResult.entrySet()) {
            String columnName = item.getKey();
            String value = item.getValue();
            Method writeMethod = writeMethodMap.get(columnName);
            Field field = columnFieldInfoMap.get(columnName);
            if (field == null) {
                continue;
            }
            writeMethod.invoke(instance, convertStr2Obj(value, field));
        }
        return instance;
    }

    /**
     * convert String -> Object
     * @param value
     * @param field
     * @return
     */
    private static Object convertStr2Obj(String value, Field field) {
        if (value.equals("null")) {
            return null;
        }
        Class<?> fieldType = field.getType();
        if (fieldType == Long.class || fieldType == long.class) {
            return StringUtils.isBlank(value) ? 0L : Long.parseLong(value);
        } else if (fieldType == Integer.class || fieldType == int.class) {
            return StringUtils.isBlank(value) ? 0 : Integer.parseInt(value);
        } else if (fieldType == Double.class || fieldType == double.class) {
            return StringUtils.isBlank(value) ? 0D : Double.parseDouble(value);
        } else if (fieldType == Float.class || fieldType == float.class) {
            return StringUtils.isBlank(value) ? 0F : Float.parseFloat(value);
        } else if (fieldType == List.class) {
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                Class<?> type = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                if (StringUtils.isBlank(value)) {
                    return Collections.emptyList();
                } else {
                    return JSON.parseArray(value, type);
                }
            }
        } else if (fieldType == String.class) {
            return value;
        }
        return JSON.parseObject(value, field.getType());
    }

    /**
     * get class info
     * @param clazz
     * @return
     */
    private static ClazzInfo getClazzInfo(Class<?> clazz) {
        ClazzInfo clazzInfo = CLAZZ_INFO_CACHE.get(clazz);
        if (clazzInfo == null) {
            synchronized (CLAZZ_INFO_CACHE) {
                clazzInfo = CLAZZ_INFO_CACHE.get(clazz);
                if (clazzInfo == null) {
                    try {
                        clazzInfo = new ClazzInfo(clazz);
                        CLAZZ_INFO_CACHE.put(clazz, clazzInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                        clazzInfo = null;
                    }
                }
            }
        }
        return clazzInfo;
    }
}
