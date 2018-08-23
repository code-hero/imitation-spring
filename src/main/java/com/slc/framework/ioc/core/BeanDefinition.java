package com.slc.framework.ioc.core;

public class BeanDefinition {
    private Class<?> origClass;//原始类
    private Object proxyObject;//代理对象

    public Class<?> getOrigClass() {
        return origClass;
    }

    public void setOrigClass(Class<?> origClass) {
        this.origClass = origClass;
    }

    public Object getProxyObject() {
        return proxyObject;
    }

    public void setProxyObject(Object proxyObject) {
        this.proxyObject = proxyObject;
    }
}
