package com.exams.sf;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-10-21
 * Time: 20:42
 * Description: 定义一个小鸟类，且该类继承自动物类，实现IFly接口
 */
public class Bird extends Animal implements IFly{
    
    private String color;
    
    public void speak() {
        System.out.println("我是小鸟,我会叫！");
    }

    @Override
    public void fly() {
        System.out.println("我是小鸟，我会飞！");
    }
}
