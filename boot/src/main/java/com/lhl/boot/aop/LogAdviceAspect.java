package com.lhl.boot.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-08-19
 * @time 14:08
 * @describe:
 */
@Aspect
@Slf4j
@Component
public class LogAdviceAspect {

    @Around("com.lhl.boot.aop.SystemArchitecture.businessService()")
    public Object consumeTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[LogAdviceAspect]: 参数：{}", JSON.toJSONString(joinPoint.getArgs()));
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("[LogAdviceAspect]: 方法:{} consume time: {}ms", joinPoint.getSignature().getName(), (endTime - startTime));
        return proceed;
    }

}
