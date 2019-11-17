package com.exams.sf;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-10-21
 * Time: 20:48
 * Description: 定义一个飞机类，且实现IFly接口
 */
public class Plane implements IFly{
    
    private String name;
    
    private String type;

    @Override
    public void fly() {
        System.out.println("我是飞机，我会飞！");
    }
}
