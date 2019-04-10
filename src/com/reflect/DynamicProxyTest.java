package com.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-10
 * Time: 10:15 PM
 * Description: 写一个 ArrayList 的动态代理类
 */
public class DynamicProxyTest {

    public static void main(String[] args) {

        final List<String> list = new ArrayList<>();
        List<String> proxyInstance = (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(),
                list.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(list,args);
                    }
                });
        
        proxyInstance.add("Hello");
        System.out.println(list);
        
    }
    
}
