package com.lhl.boot.controller;

import com.lhl.boot.service.TestService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-07-21
 * Time: 10:43
 * Description:
 */
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test")
    public String test() {
        try {
            testService.test();
        } catch (Exception e) {
            return "false";
        }
        return "true";
    }

    @GetMapping("/log")
    public String log() {
        return testService.testLog("Hello World!");
    }

}
