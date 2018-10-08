package com.demo.aop;

import com.slc.framework.ioc.anno.Service;

@Service
public class AopService {
    public void sayHello() {
        System.out.println("AopService.sayHello...");
    }

    @MyAopAnno
    public void bb() {
        System.out.println("AopService.bb...");
    }

    public void cc() {
        System.out.println("AopService.cc...");
    }
}
