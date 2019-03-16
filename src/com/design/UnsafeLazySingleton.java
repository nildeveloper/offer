package com.design;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-15
 * Time: 19:28
 * Description: 懒汉式 非线程安全
 */
public class UnsafeLazySingleton {
    
    private static UnsafeLazySingleton uniqueInstance;
    
    // private 构造函数
    private UnsafeLazySingleton() {
        
    }
    
    public static UnsafeLazySingleton getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UnsafeLazySingleton();
        }
        return uniqueInstance;
    }
}
