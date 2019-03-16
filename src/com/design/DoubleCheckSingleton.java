package com.design;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-15
 * Time: 19:37
 * Description: 双重校验锁 保证线程安全（降低锁的粒度）
 */
public class DoubleCheckSingleton {
    
    // volatile 修饰，防止指令重排序
    private volatile static DoubleCheckSingleton uniqueInstance;
    
    private DoubleCheckSingleton() {
        
    }
    
    public static DoubleCheckSingleton getUniqueInstance() {
        if (uniqueInstance == null) {  // 第一重校验  
            synchronized (DoubleCheckSingleton.class) {  // 获取当前类监视器
                if (uniqueInstance == null) {  // 第二重校验  防止两个线程同时执行第一个if 语句，造成uniqueInstance 创建两次
                    uniqueInstance = new DoubleCheckSingleton();
                }
            }
        }
        return uniqueInstance;
    }
}
