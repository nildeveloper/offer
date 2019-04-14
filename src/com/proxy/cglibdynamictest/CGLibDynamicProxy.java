package com.proxy.cglibdynamictest;

import com.proxy.jdkdynamictest.Apple;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-08
 * Time: 17:50
 * Description:
 */
public class CGLibDynamicProxy implements MethodInterceptor{

    private Object proxy;
    
    private Object getProxyObject(Object proxy) {
        this.proxy = proxy;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.proxy.getClass());  // 设置要代理的类即Apple.class
        enhancer.setCallback(this);  // 设置回调即MethodInterceptor的实现类
        return enhancer.create();
    }

    // 回调方法
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before invoking...");
        // 方法真实调用
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("After invoking...");
        return invoke;
    }

    // 无需依赖接口
    public static void main(String[] args) {
        CGLibDynamicProxy agent = new CGLibDynamicProxy();
        Apple apple = (Apple) agent.getProxyObject(new Apple());
        apple.show();
    }
}
