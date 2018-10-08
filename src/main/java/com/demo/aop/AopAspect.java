package com.demo.aop;

import com.demo.ioc.UserService;
import com.slc.framework.aop.anno.*;
import com.slc.framework.aop.core.JoinPoint;
import com.slc.framework.ioc.anno.Service;
import com.slc.framework.ioc.core.BeanFactory;

@Aspect(
        pointcut = "com.demo.aop.AopService.sayHello\\S*",
        annotation = MyAopAnno.class)
@Service
public class AopAspect {


    @Before
    public void before() {
        System.out.println("AopAspect before");
    }

    @After
    public void after() {
        System.out.println("AopAspect after");
    }

    @AfterReturning
    public void afterReturning() {
        System.out.println("AopAspect afterReturning");
    }

    @AfterThrowing
    public void afterThrowing(Exception e) {
        System.out.println("AopAspect afterThrowing");
        e.printStackTrace();
    }

    @Around
    public Object around(JoinPoint joinPoint) {
        System.out.println("AopAspect around begin");
        UserService userService = BeanFactory.getBean(UserService.class);
        userService.sayHello();
        Object proceed = joinPoint.proceed();
        System.out.println("AopAspect around end");
        return proceed;
    }
}
