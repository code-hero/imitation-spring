package com.demo.handle;

import com.slc.framework.handle.core.Handle;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyHandle extends Handle {
    @Override
    public boolean beforeHandle(Object obj, Method method, Object[] args, MethodProxy proxy) {
        System.out.println("MyHandle.beforeHandle#......");
        return super.beforeHandle(obj, method, args, proxy);
    }

    @Override
    public Object afterHandle(Object obj, Method method, Object[] args, MethodProxy proxy, Object result) {
        System.out.println("MyHandle.afterHandle#......");
        return super.afterHandle(obj, method, args, proxy, result);
    }
}
