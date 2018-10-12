package com.demo.aop.aoporig;

import com.slc.framework.ioc.anno.Service;

@Service
public class AopOrigService {

    public void sayHello() {
        System.out.println("hello UserService!");
    }
}
