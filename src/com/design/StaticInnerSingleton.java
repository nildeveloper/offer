package com.design;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-15
 * Time: 19:44
 * Description: 静态内部类 保证线程安全
 */
public class StaticInnerSingleton {
    
    private StaticInnerSingleton() {
        
    }
    
    // OutClass 类加载时，InnerClass 是没有没加载的，只有在调用 getUniqueInstance() 时InnerClass 才会被加载
    // 同时初始化 UNIQUE_INSTANCE 实例， 由JVM 保证线程的安全
    private static class InnerClass {
        private static final StaticInnerSingleton UNIQUE_INSTANCE = new StaticInnerSingleton();
    }
    
    public static StaticInnerSingleton getUniqueInstance() {
        return InnerClass.UNIQUE_INSTANCE;
    }
}
