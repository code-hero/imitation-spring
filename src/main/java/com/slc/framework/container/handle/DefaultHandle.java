package com.slc.framework.container.handle;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DefaultHandle extends Handle {

    public boolean beforeHandle(Object obj, Method method, Object[] args, MethodProxy proxy) {
        return super.beforeHandle(obj, method, args, proxy);
    }

    public Object afterHandle(Object result) {
        return super.afterHandle(result);
    }

    public void exceptionHandle(Object obj, Method method, Object[] args, MethodProxy proxy, Exception e) {
        super.exceptionHandle(obj, method, args, proxy, e);
    }
}
