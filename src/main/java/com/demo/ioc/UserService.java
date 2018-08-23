package com.demo.ioc;

import com.slc.framework.ioc.anno.Autowired;
import com.slc.framework.ioc.anno.Service;

@Service("userService")
public class UserService {
    @Autowired
    private UserInnerService userInnerService;

    public void sayHello(){
        System.out.println("hello UserService!");
        userInnerService.sayHello();
    }
}
