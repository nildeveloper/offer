package com.innerclass;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-10
 * Time: 9:46 PM
 * Description:
 */
public class InnerClassTest {
    
    public static void main(String[] args) {
//        Outter outter = new Outter();
//        Outter.Inner inner = outter.new Inner();
        Outter.Inner inner = new Outter().new Inner();  // 成员内部类对象创建方式
        Outter.StaticInner staticInner = new Outter.StaticInner();  // 静态内部类对象创建方式
    }
}

// 外部类
class Outter {
    
    private int a = 1;  
    static int b = 2;  // 静态成员
    
    public Outter(){}
    
    // 成员内部类
    class Inner {
        
        // 内部类持有一个外部类的对象
        // 成员内部类可以无条件访问外部类任意属性和方法
        public Inner() {
            System.out.println(a); 
            System.out.println(b);
        }
    }
    
    // 静态内部类
    // 不依赖外部类，不持有指向外部类的对象引用
    static class StaticInner {
        
        public StaticInner() {
//            System.out.println(a);  // 错误
            System.out.println(b);
        }
    }
    
}
