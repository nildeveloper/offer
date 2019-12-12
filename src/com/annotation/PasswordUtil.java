package com.annotation;

import org.junit.Test;
import org.omg.CORBA.portable.InvokeHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-25
 * Time: 10:00 AM
 * Description:
 */
public class PasswordUtil {
    
    @UseCase(id = 45, description = "Password must contain at least one numeric")
    public boolean validatePassword(String password) {
        return password.matches("\\w*\\d\\w*");       
    }
    
    @UseCase(id = 46)
    public String encryptPasswod(String password) {
        return new StringBuffer(password).reverse().toString();
    }
    
    @Test
    public void testUseCaseAnnotation() throws NoSuchFieldException, IllegalAccessException {
        List<Integer> useCase = new ArrayList<>();
        Collections.addAll(useCase, 42,45,46,47);
        Class clazz = PasswordUtil.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            UseCase annotation = method.getAnnotation(UseCase.class);
            if (annotation != null) {
                System.out.println("发现UseCase注解：" + annotation.id() + "  " + annotation.description());
                InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
                Field field = invocationHandler.getClass().getDeclaredField("memberValues");
                field.setAccessible(true);
                Map<String, Object> memberValues = (Map<String, Object>) field.get(invocationHandler);
                System.out.println("修改前：" + memberValues.get("description"));
                memberValues.put("description", "修改后的描述");
                System.out.println("修改后：" + memberValues.get("description"));
                useCase.remove(new Integer(annotation.id()));
            }
        }

        for (Integer i : useCase) {
            System.out.println("Warning: Missing use case - " + i);
        }
    }
    
}
