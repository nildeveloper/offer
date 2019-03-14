package com.proxy.statictest;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-08
 * Time: 17:20
 * Description:
 */
public class StaticProxy {

    public static void main(String[] args) {
        // 创建演员对象
        Actor actor = new Actor("I am a famous actor!");
        // 创建代理对象
        Agent agent = new Agent("Hello, I am proxy", "That's all!", actor);
        // 代理对象发言
        agent.speak();
    }
}
