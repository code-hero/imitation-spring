package com.demo.ioc;

import com.slc.framework.ioc.anno.Service;

@Service
public class UserInnerService {
    public void sayHello(){
        System.out.println("hello UserInnerService");
    }
}
