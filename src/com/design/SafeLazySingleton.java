package com.design;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-15
 * Time: 19:33
 * Description: 懒汉式 线程安全（Synchronized）
 */
public class SafeLazySingleton {
    
    private static SafeLazySingleton uniqueInstance;
    
    private SafeLazySingleton() {
        
    }
    
    // 同步方法，保证每次只有一个线程可以进入同步区域，效率降低
    public static synchronized SafeLazySingleton getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SafeLazySingleton();
        }
        return uniqueInstance;
    }
}
