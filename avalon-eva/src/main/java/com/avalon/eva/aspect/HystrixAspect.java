package com.avalon.eva.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 服务降级切面编程
 */
@Component
@Aspect
public class HystrixAspect {

    //前置通知
    @Before("execution(* com.avalon.eva.service.*.hystrix.*.*(..))")
    public void beforeHystrix(){
        System.out.println("前置通知");
    }

    // 后置通知
    @After("execution(* com.avalon.eva.service.*.hystrix.*.*(..))")
    public void afterHystrix(){
        System.out.println("后置通知");
    }

    //运行通知
    @AfterReturning("execution(* com.avalon.eva.service.*.hystrix.*.*(..))")
    public void afterReturningHystrix(){
        System.out.println("运行通知.....");
    }
    //异常通知
    @AfterThrowing("execution(* com.avalon.eva.service.*.hystrix.*.*(..))")
    public void afterThrowingHystrix(){
        System.out.println("异常通知???");
    }

    //环绕通知
    @Around("execution(* com.avalon.eva.service.*.hystrix.*.*(..))")
    public void aroundHystrix(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("我是环绕通知-前");
        proceedingJoinPoint.proceed();
        System.out.println("我是环绕通知-后");

    }






}



