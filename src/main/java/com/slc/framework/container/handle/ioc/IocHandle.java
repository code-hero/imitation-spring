package com.slc.framework.container.handle.ioc;

import com.slc.framework.container.handle.Handle;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class IocHandle extends Handle {
    @Override
    public boolean beforeHandle(Object obj, Method method, Object[] args, MethodProxy proxy) {
        System.out.println("IocHandle beforeHandle");
        return super.beforeHandle(obj, method, args, proxy);
    }

    @Override
    public Object afterHandle(Object result) {
        System.out.println("IocHandle afterHandle");
        return super.afterHandle(result);
    }

    @Override
    public void exceptionHandle(Object obj, Method method, Object[] args, MethodProxy proxy, Exception e) {
        System.out.println("IocHandle exceptionHandle");
        super.exceptionHandle(obj, method, args, proxy, e);
    }
}
