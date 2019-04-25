package com.annotation;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-25
 * Time: 10:00 AM
 * Description:
 */
public class PasswordUtil {
    
    @UseCase(id = 45, description = "Password must contain at least ont numeric")
    public boolean validatePassword(String password) {
        return password.matches("\\w*\\d\\w*");       
    }
    
    @UseCase(id = 46)
    public String encryptPasswod(String password) {
        return new StringBuffer(password).reverse().toString();
    }
    
    @Test
    public void testUseCaseAnnotation() {
        List<Integer> useCase = new ArrayList<>();
        Collections.addAll(useCase, 42,45,46,47);
        Class clazz = PasswordUtil.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            UseCase annotation = method.getAnnotation(UseCase.class);
            if (annotation != null) {
                System.out.println("发现UseCase注解：" + annotation.id() + "  " + annotation.description());
                useCase.remove(new Integer(annotation.id()));
            }
        }

        for (Integer i : useCase) {
            System.out.println("Warning: Missing use case - " + i);
        }
    }
    
}
