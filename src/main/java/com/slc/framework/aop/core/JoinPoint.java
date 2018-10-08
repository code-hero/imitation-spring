package com.slc.framework.aop.core;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class JoinPoint {
    private Object obj;
    private Method method;
    private Object[] args;
    private MethodProxy proxy;

    public JoinPoint(Object obj, Method method, Object[] args, MethodProxy proxy) {
        this.obj = obj;
        this.method = method;
        this.args = args;
        this.proxy = proxy;
    }

    public Object proceed() {
        Object result = null;
        try {
            result = proxy.invokeSuper(obj, args);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
        return result;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public MethodProxy getProxy() {
        return proxy;
    }

    public void setProxy(MethodProxy proxy) {
        this.proxy = proxy;
    }
}
