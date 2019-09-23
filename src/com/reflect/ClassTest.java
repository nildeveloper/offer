package com.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: haolu.liu
 * Date: 2019-09-05 11:12
 * Description:
 */
public class ClassTest {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Test
    public void test1() {
        ClassTest test = new ClassTest();
        Class clazz = test.getClass();
        Class clazz1 = ClassTest.class;

        Method[] declaredMethods = clazz.getDeclaredMethods();
        Field[] fields = clazz.getDeclaredFields();
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Method method : declaredMethods) {
            System.out.println(method);
        }
        for (Field field : fields) {
            System.out.println(field);
        }
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
    }
}
