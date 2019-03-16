package com.design;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-15
 * Time: 19:31
 * Description: 饿汉式 线程安全
 */
public class SafeHungrySingleton {
    
    private static SafeHungrySingleton uniqueInstance = new SafeHungrySingleton();
    
    private SafeHungrySingleton() {
        
    }
    
    public static SafeHungrySingleton getUniqueInstance() {
        return uniqueInstance;
    }
}
