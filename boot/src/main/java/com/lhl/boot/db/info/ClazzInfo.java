package com.lhl.boot.db.info;

import com.lhl.boot.db.annotation.DBColumn;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-12-28
 * @time 16:16
 * @describe: Class描述信息封装
 */
public class ClazzInfo {

    private Field pkInfo;

    /**
     * columnName -> Field
     */
    private Map<String, Field> columnFieldInfoMap;

    /**
     * columnName -> fieldGetMethod
     */
    private Map<String, Method> readMethodMap;

    /**
     * columnName -> fieldSetMethod
     */
    private Map<String, Method> writeMethodMap;

    public ClazzInfo(Class<?> clazz) throws Exception {
        readMethodMap = new HashMap<>();
        writeMethodMap = new HashMap<>();
        columnFieldInfoMap = getFieldInfoMap(clazz);
    }

    private Map<String, Field> getFieldInfoMap(Class<?> clazz) throws Exception {
        Field[] declaredFields = clazz.getDeclaredFields();
        Map<String, Field> fieldInfoMap = new HashMap<>();
        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(DBColumn.class)) {
                continue;
            }
            // 构造fieldInfoMap
            fieldInfoMap.put(field.getName(), field);
        }
        if (fieldInfoMap.isEmpty()) {
            throw new IllegalArgumentException("class not exist db column");
        }
        // 构造set、get method map
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Introspector.USE_ALL_BEANINFO);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String fieldName = propertyDescriptor.getName();
            Field field = fieldInfoMap.get(fieldName);
            if (field == null) {
                continue;
            }
            DBColumn annotation = field.getAnnotation(DBColumn.class);
            if (annotation == null) {
                continue;
            }
            readMethodMap.put(annotation.name(), propertyDescriptor.getReadMethod());
            writeMethodMap.put(annotation.name(), propertyDescriptor.getWriteMethod());
        }
        Map<String, Field> columnMap = new HashMap<>();
        for (Map.Entry<String, Field> entry : fieldInfoMap.entrySet()) {
            Field field = entry.getValue();
            DBColumn annotation = field.getAnnotation(DBColumn.class);
            if (annotation.pk()) {
                this.pkInfo = field;
            }
            columnMap.put(annotation.name(), field);
        }
        return columnMap;
    }

    public Field getPkInfo() {
        return pkInfo;
    }

    public Map<String, Field> getColumnFieldInfoMap() {
        return columnFieldInfoMap;
    }

    public Map<String, Method> getReadMethodMap() {
        return readMethodMap;
    }

    public Map<String, Method> getWriteMethodMap() {
        return writeMethodMap;
    }
}
