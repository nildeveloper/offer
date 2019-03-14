package com.proxy.statictest;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-08
 * Time: 17:16
 * Description:
 */
public class Agent implements Person {
    
    private String before;
    
    private String after;
    
    private Actor actor;

    public Agent(String before, String after, Actor actor) {
        this.before = before;
        this.after = after;
        this.actor = actor;
    }

    @Override
    public void speak() {
        // 代理说
        System.out.println("Before actor speak~~~" + before);
        // 演员说
        this.actor.speak();
        // 代理说
        System.out.println("After actor speak~~~" + after);
    }
}
