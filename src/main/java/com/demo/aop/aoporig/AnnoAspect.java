package com.demo.aop.aoporig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AnnoAspect {
    @Pointcut("execution(* com.demo.aop.aoporig.AopOrigService.sayHello(..))")
    public void jointPoint() {
    }
    @Before("jointPoint()")
    public void before(JoinPoint joinPoint) {
        System.out.println("AnnoAspect before say");
    }
    @After("jointPoint()")
    public void after(JoinPoint joinPoint) {
        System.out.println("AnnoAspect after say");
    }
//    @Around("jointPoint()")
//    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        System.out.println("AnnoAspect around say begin");
//        proceedingJoinPoint.proceed();
//        System.out.println("AnnoAspect around say end");
//    }
    @AfterReturning("jointPoint()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("AnnoAspect afterReturning say");
    }
    @AfterThrowing("jointPoint()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("AnnoAspect afterThrowing say");
    }
}
