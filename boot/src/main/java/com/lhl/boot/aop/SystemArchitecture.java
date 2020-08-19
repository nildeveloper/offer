package com.lhl.boot.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-08-19
 * @time 14:02
 * @describe:
 * @Pointcut 中使用了 execution 来正则匹配方法签名，这也是最常用的
 *
 * within：指定所在类或所在包下面的方法（Spring AOP 独有）
 *
 * 如 @Pointcut("within(com.javadoop.springaoplearning.service..*)")
 *
 * @annotation：方法上具有特定的注解，如 @Subscribe 用于订阅特定的事件。
 *
 * 如 @Pointcut("execution( .*(..)) && @annotation(com.javadoop.annotation.Subscribe)")
 *
 * bean(idOrNameOfBean)：匹配 bean 的名字（Spring AOP 独有）
 *
 * 如 @Pointcut("bean(*Service)")
 *
 * 通常 "." 代表一个包名，".." 代表包及其子包，方法参数任意匹配使用两个点 ".."。
 */
@Aspect
public class SystemArchitecture {

    @Pointcut("within(com.lhl.boot.controller..*)")
    public void inWebLayer(){

    }

    @Pointcut("within(com.lhl.boot.service..*)")
    public void inServiceLayer() {

    }

    @Pointcut("within(com.lhl.boot.dao..*)")
    public void inDataAccessLayer() {

    }

    // service 实现，注意这里指的是方法实现，其实通常也可以使用 bean(*ServiceImpl)
    @Pointcut("execution(* com.lhl.boot.service.*.*(..))")
    public void businessService() {

    }

    @Pointcut("execution(* com.lhl.boot.dao.*.*(..))")
    public void dataAccessOperation() {

    }
}
