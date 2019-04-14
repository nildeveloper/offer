package com.proxy.jdkdynamictest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-08
 * Time: 17:27
 * Description: 使用JDK动态代理接口 实现动态代理类, 用来获取 proxy 代理对象
 */
public class JdkDynamicProxy {
    
    // 静态内部来，实现InvocationHandler接口
    static class MyHandler implements InvocationHandler {
        
        private Object proxy;

        public MyHandler(Object proxy) {
            this.proxy = proxy;
        }

        // 自定义Invoke 方法
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Before invoking...");
            Object result = method.invoke(this.proxy, args);
            System.out.println("After invoking...");
            return result;
        }
    }
    
    // 返回一个被修改过的对象（两个参数：接口对象、实现类对象）
    public static Object getProxyObject(Class interfaceClazz, Object proxy) {
        return Proxy.newProxyInstance(interfaceClazz.getClassLoader(), interfaceClazz.getInterfaces(), new MyHandler(proxy));
    }

    public static void main(String[] args) {
        // 返回对象必须为接口
        Fruit fruit = (Fruit) JdkDynamicProxy.getProxyObject(Fruit.class, new Apple());
        fruit.show();
    }
    
}
