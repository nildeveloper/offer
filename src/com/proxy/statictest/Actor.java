package com.proxy.statictest;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-08
 * Time: 17:16
 * Description:
 */
public class Actor implements Person {
    
    private String content;

    public Actor(String content) {
        this.content = content;
    }

    @Override
    public void speak() {
        System.out.println(content);
    }
}
