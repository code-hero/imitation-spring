package com.demo.aop;

import com.slc.framework.aop.anno.*;
import com.slc.framework.aop.core.JoinPoint;
import com.slc.framework.ioc.anno.Service;

@Aspect(
        pointcut = "com.demo.aop.AopService.sayHello\\S*")
@Service
public class AopAspect2 {
    @Before
    public void before() {
        System.out.println("AopAspect2 before");
    }

    @After
    public void after() {
        System.out.println("AopAspect2 after");
    }

    @AfterReturning
    public void afterReturning() {
        System.out.println("AopAspect2 afterReturning");
    }

    @AfterThrowing
    public void afterThrowing(Exception e) {
        System.out.println("AopAspect2 afterThrowing");
        e.printStackTrace();
    }

    @Around
    public Object around(JoinPoint joinPoint) {
        System.out.println("AopAspect2 around begin");
        Object proceed = joinPoint.proceed();
        System.out.println("AopAspect2 around end");
        return proceed;
    }
}
